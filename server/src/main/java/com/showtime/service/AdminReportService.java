package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.showtime.entity.*;
import com.showtime.mapper.*;
import com.showtime.vo.AdminDashboardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminReportService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final PaymentMapper paymentMapper;
    private final UserMapper userMapper;
    private final EventMapper eventMapper;
    private final SeatMapper seatMapper;
    private final EventSessionMapper sessionMapper;

    private static final DateTimeFormatter DISP_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AdminDashboardVO dashboard() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = todayStart.plusDays(1);

        AdminDashboardVO vo = new AdminDashboardVO();

        // Today's orders
        List<Order> todayOrders = orderMapper.selectList(
            new LambdaQueryWrapper<Order>()
                .ge(Order::getCreatedAt, todayStart)
                .lt(Order::getCreatedAt, todayEnd));
        vo.setTodayOrderCount(todayOrders.size());
        vo.setTodayRevenue(todayOrders.stream()
            .filter(o -> "paid".equals(o.getStatus()))
            .map(Order::getTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add));

        // Today's new users
        vo.setTodayNewUsers(userMapper.selectCount(
            new LambdaQueryWrapper<User>()
                .ge(User::getCreatedAt, todayStart)
                .lt(User::getCreatedAt, todayEnd)));

        // Active events (status = 1)
        vo.setActiveEventCount(eventMapper.selectCount(
            new LambdaQueryWrapper<Event>().eq(Event::getStatus, 1)));

        // Recent orders (last 10)
        List<Order> recent = orderMapper.selectList(
            new LambdaQueryWrapper<Order>()
                .orderByDesc(Order::getCreatedAt)
                .last("LIMIT 10"));
        vo.setRecentOrders(recent.stream().map(o -> {
            var ro = new AdminDashboardVO.RecentOrder();
            ro.setOrderNo(o.getOrderNo());
            ro.setAmount(o.getTotalAmount());
            ro.setStatus(o.getStatus());
            ro.setCreatedAt(o.getCreatedAt().format(DISP_FMT));
            return ro;
        }).toList());

        // Top events by sales (top 5)
        List<Order> allPaid = orderMapper.selectList(
            new LambdaQueryWrapper<Order>().eq(Order::getStatus, "paid"));
        Map<Integer, List<Order>> byEvent = new HashMap<>();
        for (Order o : allPaid) {
            List<OrderItem> items = orderItemMapper.selectList(
                new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, o.getId()));
            for (OrderItem item : items) {
                Seat seat = seatMapper.selectById(item.getSeatId());
                if (seat != null) {
                    EventSession session = sessionMapper.selectById(seat.getSessionId());
                    if (session != null) {
                        byEvent.computeIfAbsent(session.getEventId(), k -> new ArrayList<>()).add(o);
                        break;
                    }
                }
            }
        }
        vo.setTopEvents(byEvent.entrySet().stream()
            .sorted((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()))
            .limit(5)
            .map(e -> {
                var rank = new AdminDashboardVO.EventSalesRank();
                Event event = eventMapper.selectById(e.getKey());
                rank.setEventTitle(event != null ? event.getTitle() : "未知");
                rank.setTicketCount(e.getValue().size());
                rank.setRevenue(e.getValue().stream()
                    .map(Order::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
                return rank;
            }).toList());

        return vo;
    }
}
