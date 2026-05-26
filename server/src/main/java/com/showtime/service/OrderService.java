package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.showtime.common.BizException;
import com.showtime.common.PageResult;
import com.showtime.common.ResultCode;
import com.showtime.entity.*;
import com.showtime.mapper.*;
import com.showtime.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final PaymentMapper paymentMapper;
    private final SeatMapper seatMapper;
    private final EventSessionMapper sessionMapper;
    private final EventMapper eventMapper;
    private final VenueMapper venueMapper;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DISP_DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // ==================== 私有辅助 ====================

    /** 验证座位归属、锁定状态、锁定时效，返回有效座位列表 */
    private List<Seat> validateAndGetSeats(Integer sessionId, List<Integer> seatIds, Integer userId) {
        List<Seat> seats = seatMapper.selectBatchIds(seatIds);
        if (seats.size() != seatIds.size()) {
            throw new BizException(ResultCode.NOT_FOUND, "部分座位不存在");
        }
        LocalDateTime now = LocalDateTime.now();
        for (Seat seat : seats) {
            if (!seat.getSessionId().equals(sessionId)) {
                throw new BizException(ResultCode.BAD_REQUEST, "座位不属于该场次");
            }
            if (!"locked".equals(seat.getStatus())) {
                throw new BizException(ResultCode.SEAT_UNAVAILABLE);
            }
            if (!userId.equals(seat.getLockUserId())) {
                throw new BizException(ResultCode.SEAT_LOCKED_BY_OTHER);
            }
            if (seat.getLockTime() != null && seat.getLockTime().plusMinutes(15).isBefore(now)) {
                throw new BizException(ResultCode.LOCK_TIMEOUT);
            }
        }
        return seats;
    }

    /** 读取订单并校验归属 */
    private Order getOwnedOrder(Integer orderId, Integer userId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BizException(ResultCode.ORDER_NOT_FOUND);
        }
        if (!order.getUserId().equals(userId)) {
            throw new BizException(ResultCode.FORBIDDEN);
        }
        return order;
    }

    /** 读取 session → event → venue 链，event 不存在则抛异常 */
    private EventSession requireSession(Integer sessionId) {
        EventSession session = sessionMapper.selectById(sessionId);
        if (session == null) {
            throw new BizException(ResultCode.NOT_FOUND, "场次不存在");
        }
        return session;
    }

    private Event requireEvent(Integer eventId) {
        Event event = eventMapper.selectById(eventId);
        if (event == null) {
            throw new BizException(ResultCode.NOT_FOUND, "演出不存在");
        }
        return event;
    }

    // ==================== 订单预览 ====================

    public OrderPreviewVO preview(Integer sessionId, List<Integer> seatIds, Integer userId) {
        List<Seat> seats = validateAndGetSeats(sessionId, seatIds, userId);

        EventSession session = requireSession(sessionId);
        Event event = requireEvent(session.getEventId());
        Venue venue = venueMapper.selectById(event.getVenueId());

        OrderPreviewVO vo = new OrderPreviewVO();
        OrderPreviewVO.EventInfo eventInfo = new OrderPreviewVO.EventInfo();
        eventInfo.setTitle(event.getTitle());
        eventInfo.setPosterUrl(event.getPosterUrl());
        eventInfo.setSessionTime(session.getSessionTime().format(DISP_DATE_FMT));
        eventInfo.setVenueName(venue != null ? venue.getName() : "");
        vo.setEventInfo(eventInfo);

        List<OrderPreviewVO.SeatPreview> previewList = seats.stream().map(seat -> {
            OrderPreviewVO.SeatPreview sp = new OrderPreviewVO.SeatPreview();
            sp.setId(seat.getId());
            sp.setZoneName(seat.getZoneName());
            sp.setSeatRow(seat.getSeatRow());
            sp.setSeatCol(seat.getSeatCol());
            sp.setPrice(seat.getPrice());
            return sp;
        }).toList();
        vo.setSeats(previewList);

        BigDecimal totalPrice = previewList.stream()
            .map(OrderPreviewVO.SeatPreview::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        vo.setTotalPrice(totalPrice);
        vo.setSeatCount(previewList.size());

        return vo;
    }

    // ==================== 创建订单 ====================

    @Transactional
    public CreateOrderResult createOrder(Integer sessionId, List<Integer> seatIds, Integer userId) {
        List<Seat> seats = validateAndGetSeats(sessionId, seatIds, userId);

        BigDecimal totalAmount = seats.stream()
            .map(Seat::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 生成订单号
        String today = LocalDateTime.now().format(DATE_FMT);
        long todayCount = orderMapper.selectCount(
            new LambdaQueryWrapper<Order>().likeRight(Order::getOrderNo, today));
        String orderNo = String.format("ORD%s%04d", today, todayCount + 1);

        // 创建订单
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus("pending");
        order.setTicketCount(seatIds.size());
        orderMapper.insert(order);

        // 创建订单明细
        for (Seat seat : seats) {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setSeatId(seat.getId());
            item.setPrice(seat.getPrice());
            orderItemMapper.insert(item);
        }

        // 座位改为已售（条件更新 + 检查影响行数，防并发）
        LambdaUpdateWrapper<Seat> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Seat::getId, seatIds)
                     .eq(Seat::getStatus, "locked")
                     .eq(Seat::getLockUserId, userId)
                     .set(Seat::getStatus, "sold")
                     .set(Seat::getLockTime, null)
                     .set(Seat::getLockUserId, null);
        int updated = seatMapper.update(null, updateWrapper);
        if (updated < seatIds.size()) {
            throw new BizException(ResultCode.SEAT_UNAVAILABLE, "座位状态已变化，请重新选座");
        }

        // 创建支付记录（初始 pending）
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setPayMethod("alipay");
        payment.setAmount(totalAmount);
        payment.setStatus("pending");
        paymentMapper.insert(payment);

        log.info("订单创建: orderNo={}, userId={}, total={}, tickets={}", orderNo, userId, totalAmount, seatIds.size());

        return new CreateOrderResult(order.getId(), orderNo);
    }

    // ==================== 支付 ====================

    @Transactional
    public void payOrder(Integer orderId, Integer userId) {
        Order order = getOwnedOrder(orderId, userId);
        if (!"pending".equals(order.getStatus())) {
            throw new BizException(ResultCode.ORDER_CANNOT_PAY);
        }

        // 10% 模拟支付失败
        if (Math.random() < 0.1) {
            log.warn("支付模拟失败: orderNo={}", order.getOrderNo());
            throw new BizException(ResultCode.PAYMENT_FAILED, "支付失败，请稍后重试");
        }

        LocalDateTime now = LocalDateTime.now();
        String tradeNo = "SIM" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

        // 条件更新订单状态（防并发：只更新 pending 订单）
        LambdaUpdateWrapper<Order> orderUpdate = new LambdaUpdateWrapper<>();
        orderUpdate.eq(Order::getId, orderId)
                   .eq(Order::getStatus, "pending")
                   .set(Order::getStatus, "paid")
                   .set(Order::getPaidAt, now);
        int orderAffected = orderMapper.update(null, orderUpdate);
        if (orderAffected == 0) {
            throw new BizException(ResultCode.ORDER_CANNOT_PAY, "订单状态已变更");
        }

        LambdaUpdateWrapper<Payment> payUpdate = new LambdaUpdateWrapper<>();
        payUpdate.eq(Payment::getOrderId, orderId)
                 .set(Payment::getStatus, "success")
                 .set(Payment::getTradeNo, tradeNo)
                 .set(Payment::getPaidAt, now);
        paymentMapper.update(null, payUpdate);

        log.info("支付成功: orderNo={}, tradeNo={}, amount={}", order.getOrderNo(), tradeNo, order.getTotalAmount());
    }

    // ==================== 取消订单 ====================

    @Transactional
    public void cancelOrder(Integer orderId, Integer userId) {
        Order order = getOwnedOrder(orderId, userId);
        if (!"pending".equals(order.getStatus())) {
            throw new BizException(ResultCode.ORDER_CANNOT_CANCEL);
        }

        // 条件更新订单状态（防并发）
        LambdaUpdateWrapper<Order> orderUpdate = new LambdaUpdateWrapper<>();
        orderUpdate.eq(Order::getId, orderId)
                   .eq(Order::getStatus, "pending")
                   .set(Order::getStatus, "cancelled")
                   .set(Order::getCancelledAt, LocalDateTime.now());
        int orderAffected = orderMapper.update(null, orderUpdate);
        if (orderAffected == 0) {
            throw new BizException(ResultCode.ORDER_CANNOT_CANCEL, "订单状态已变更");
        }

        // 释放座位
        List<OrderItem> items = orderItemMapper.selectList(
            new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, orderId));
        List<Integer> seatIds = items.stream().map(OrderItem::getSeatId).toList();
        if (!seatIds.isEmpty()) {
            LambdaUpdateWrapper<Seat> seatUpdate = new LambdaUpdateWrapper<>();
            seatUpdate.in(Seat::getId, seatIds)
                      .eq(Seat::getStatus, "sold")
                      .set(Seat::getStatus, "available")
                      .set(Seat::getLockTime, null)
                      .set(Seat::getLockUserId, null);
            seatMapper.update(null, seatUpdate);
        }

        // 支付记录标为失败
        LambdaUpdateWrapper<Payment> payUpdate = new LambdaUpdateWrapper<>();
        payUpdate.eq(Payment::getOrderId, orderId)
                 .set(Payment::getStatus, "failed");
        paymentMapper.update(null, payUpdate);

        log.info("订单取消: orderNo={}, userId={}", order.getOrderNo(), userId);
    }

    // ==================== 订单列表 ====================

    public PageResult<OrderCardVO> list(Integer userId, String status, Integer page, Integer pageSize) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId);
        if (status != null && !status.isBlank()) {
            wrapper.eq(Order::getStatus, status);
        }
        wrapper.orderByDesc(Order::getCreatedAt);

        Page<Order> orderPage = orderMapper.selectPage(
            new Page<>(page != null ? page : 1, pageSize != null ? pageSize : 10), wrapper);

        List<OrderCardVO> vos = assembleOrderCards(orderPage.getRecords());
        return PageResult.of(vos, orderPage.getTotal(), (int) orderPage.getCurrent(), (int) orderPage.getSize());
    }

    private List<OrderCardVO> assembleOrderCards(List<Order> orders) {
        if (orders.isEmpty()) return List.of();

        List<Integer> orderIds = orders.stream().map(Order::getId).toList();

        List<OrderItem> allItems = orderItemMapper.selectList(
            new LambdaQueryWrapper<OrderItem>().in(OrderItem::getOrderId, orderIds));
        Map<Integer, List<OrderItem>> itemsByOrder = allItems.stream()
            .collect(Collectors.groupingBy(OrderItem::getOrderId));

        List<Integer> seatIds = allItems.stream().map(OrderItem::getSeatId).distinct().toList();
        List<Seat> allSeats = seatIds.isEmpty() ? List.of() : seatMapper.selectBatchIds(seatIds);
        Map<Integer, Seat> seatMap = allSeats.stream()
            .collect(Collectors.toMap(Seat::getId, s -> s));

        List<Integer> sessionIds = allSeats.stream().map(Seat::getSessionId).distinct().toList();
        List<EventSession> sessions = sessionIds.isEmpty() ? List.of() : sessionMapper.selectBatchIds(sessionIds);
        Map<Integer, EventSession> sessionMap = sessions.stream()
            .collect(Collectors.toMap(EventSession::getId, s -> s));

        List<Integer> eventIds = sessions.stream().map(EventSession::getEventId).distinct().toList();
        List<Event> events = eventIds.isEmpty() ? List.of() : eventMapper.selectBatchIds(eventIds);
        Map<Integer, Event> eventMap = events.stream()
            .collect(Collectors.toMap(Event::getId, e -> e));

        return orders.stream().map(order -> {
            OrderCardVO vo = new OrderCardVO();
            vo.setId(order.getId());
            vo.setOrderNo(order.getOrderNo());
            vo.setTotalAmount(order.getTotalAmount());
            vo.setTicketCount(order.getTicketCount());
            vo.setStatus(order.getStatus());
            vo.setCreatedAt(order.getCreatedAt());

            List<OrderItem> items = itemsByOrder.getOrDefault(order.getId(), List.of());
            if (!items.isEmpty()) {
                Seat firstSeat = seatMap.get(items.get(0).getSeatId());
                if (firstSeat != null) {
                    EventSession session = sessionMap.get(firstSeat.getSessionId());
                    if (session != null) {
                        vo.setSessionTime(session.getSessionTime().format(DISP_DATE_FMT));
                        Event event = eventMap.get(session.getEventId());
                        if (event != null) {
                            vo.setEventTitle(event.getTitle());
                            vo.setPosterUrl(event.getPosterUrl());
                        }
                    }
                }
            }
            return vo;
        }).toList();
    }

    // ==================== 订单详情 ====================

    public OrderDetailVO detail(Integer orderId, Integer userId) {
        Order order = getOwnedOrder(orderId, userId);

        List<OrderItem> items = orderItemMapper.selectList(
            new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, orderId));

        List<Integer> seatIds = items.stream().map(OrderItem::getSeatId).toList();
        List<Seat> seats = seatIds.isEmpty() ? List.of() : seatMapper.selectBatchIds(seatIds);
        Map<Integer, Seat> seatMap = seats.stream()
            .collect(Collectors.toMap(Seat::getId, s -> s));

        Event event = null;
        Venue venue = null;
        EventSession session = null;
        if (!seats.isEmpty()) {
            session = sessionMapper.selectById(seats.get(0).getSessionId());
            if (session != null) {
                event = eventMapper.selectById(session.getEventId());
                if (event != null) {
                    venue = venueMapper.selectById(event.getVenueId());
                }
            }
        }

        Payment payment = paymentMapper.selectOne(
            new LambdaQueryWrapper<Payment>().eq(Payment::getOrderId, orderId));

        OrderDetailVO vo = new OrderDetailVO();
        vo.setId(order.getId());
        vo.setOrderNo(order.getOrderNo());
        vo.setStatus(order.getStatus());
        vo.setTotalAmount(order.getTotalAmount());
        vo.setTicketCount(order.getTicketCount());
        vo.setPaidAt(order.getPaidAt());
        vo.setCancelledAt(order.getCancelledAt());
        vo.setCreatedAt(order.getCreatedAt());

        if (event != null) {
            OrderDetailVO.EventInfo eventInfo = new OrderDetailVO.EventInfo();
            eventInfo.setId(event.getId());
            eventInfo.setTitle(event.getTitle());
            eventInfo.setPosterUrl(event.getPosterUrl());
            eventInfo.setVenueName(venue != null ? venue.getName() : "");
            eventInfo.setSessionTime(session != null
                ? session.getSessionTime().format(DISP_DATE_FMT) : "");
            vo.setEvent(eventInfo);
        }

        List<OrderDetailVO.TicketInfo> tickets = items.stream().map(item -> {
            OrderDetailVO.TicketInfo ticket = new OrderDetailVO.TicketInfo();
            Seat seat = seatMap.get(item.getSeatId());
            if (seat != null) {
                ticket.setZoneName(seat.getZoneName());
                ticket.setSeatRow(seat.getSeatRow());
                ticket.setSeatCol(seat.getSeatCol());
                ticket.setPrice(item.getPrice());
            }
            return ticket;
        }).toList();
        vo.setTickets(tickets);

        if (payment != null) {
            OrderDetailVO.PaymentInfo paymentInfo = new OrderDetailVO.PaymentInfo();
            paymentInfo.setPayMethod(payment.getPayMethod());
            paymentInfo.setAmount(payment.getAmount());
            paymentInfo.setStatus(payment.getStatus());
            paymentInfo.setTradeNo(payment.getTradeNo());
            paymentInfo.setPaidAt(payment.getPaidAt());
            vo.setPayment(paymentInfo);
        }

        return vo;
    }

    // ==================== 内部类型 ====================

    /** 创建订单返回结果 */
    public record CreateOrderResult(Integer orderId, String orderNo) {}
}
