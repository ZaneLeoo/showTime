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
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventMapper eventMapper;
    private final EventSessionMapper sessionMapper;
    private final CategoryMapper categoryMapper;
    private final VenueMapper venueMapper;
    private final SeatMapper seatMapper;

    private static final String[] ZONE_COLORS = {"#8b1a2b", "#c9a84c", "#2d5a3d", "#1e3a5f", "#5c2d6e"};

    // ==================== 演出列表 ====================

    public PageResult<EventCardVO> list(Integer categoryId, String city, String keyword,
                                         String dateStart, String dateEnd,
                                         String sortBy, Integer page, Integer pageSize) {
        LambdaQueryWrapper<Event> wrapper = new LambdaQueryWrapper<>();

        if (categoryId != null) {
            wrapper.eq(Event::getCategoryId, categoryId);
        }
        if (keyword != null && !keyword.isBlank()) {
            wrapper.like(Event::getTitle, keyword);
        }
        if (city != null && !city.isBlank()) {
            List<Integer> venueIds = venueMapper.selectList(
                new LambdaQueryWrapper<Venue>().eq(Venue::getCity, city)
            ).stream().map(Venue::getId).toList();
            if (venueIds.isEmpty()) {
                return PageResult.of(List.of(), 0L, page, pageSize);
            }
            wrapper.in(Event::getVenueId, venueIds);
        }
        if (dateStart != null || dateEnd != null) {
            LambdaQueryWrapper<EventSession> sw = new LambdaQueryWrapper<>();
            if (dateStart != null) sw.ge(EventSession::getSessionTime, LocalDateTime.parse(dateStart));
            if (dateEnd != null) sw.le(EventSession::getSessionTime, LocalDateTime.parse(dateEnd));
            List<Integer> eventIds = sessionMapper.selectList(sw).stream()
                .map(EventSession::getEventId).distinct().toList();
            if (eventIds.isEmpty()) {
                return PageResult.of(List.of(), 0L, page, pageSize);
            }
            wrapper.in(Event::getId, eventIds);
        }

        wrapper.orderByDesc(Event::getId);

        Page<Event> eventPage = eventMapper.selectPage(
            new Page<>(page != null ? page : 1, pageSize != null ? pageSize : 12), wrapper);

        List<EventCardVO> vos = assembleEventCards(eventPage.getRecords());
        return PageResult.of(vos, eventPage.getTotal(), (int) eventPage.getCurrent(), (int) eventPage.getSize());
    }

    private List<EventCardVO> assembleEventCards(List<Event> events) {
        if (events.isEmpty()) return List.of();

        List<Integer> eventIds = events.stream().map(Event::getId).toList();
        List<Integer> categoryIds = events.stream().map(Event::getCategoryId).distinct().toList();
        List<Integer> venueIds = events.stream().map(Event::getVenueId).distinct().toList();

        Map<Integer, String> categoryMap = categoryMapper.selectBatchIds(categoryIds).stream()
            .collect(Collectors.toMap(Category::getId, Category::getName));
        Map<Integer, Venue> venueMap = venueMapper.selectBatchIds(venueIds).stream()
            .collect(Collectors.toMap(Venue::getId, v -> v));

        List<EventSession> allSessions = sessionMapper.selectList(
            new LambdaQueryWrapper<EventSession>().in(EventSession::getEventId, eventIds));
        Map<Integer, List<EventSession>> sessionMap = allSessions.stream()
            .collect(Collectors.groupingBy(EventSession::getEventId));

        List<Integer> sessionIds = allSessions.stream().map(EventSession::getId).toList();
        final Map<Integer, BigDecimal> sessionMinPrice;
        if (!sessionIds.isEmpty()) {
            List<Seat> seats = seatMapper.selectList(
                new LambdaQueryWrapper<Seat>().in(Seat::getSessionId, sessionIds));
            sessionMinPrice = seats.stream()
                .collect(Collectors.groupingBy(Seat::getSessionId,
                    Collectors.collectingAndThen(
                        Collectors.minBy(Comparator.comparing(Seat::getPrice)),
                        opt -> opt.map(Seat::getPrice).orElse(BigDecimal.ZERO))));
        } else {
            sessionMinPrice = Map.of();
        }

        return events.stream().map(event -> {
            EventCardVO vo = new EventCardVO();
            vo.setId(event.getId());
            vo.setTitle(event.getTitle());
            vo.setPosterUrl(event.getPosterUrl());
            vo.setStatus(event.getStatus());
            vo.setCategoryName(categoryMap.getOrDefault(event.getCategoryId(), ""));
            Venue venue = venueMap.get(event.getVenueId());
            if (venue != null) {
                vo.setVenueName(venue.getName());
                vo.setCity(venue.getCity());
            }
            List<EventSession> sessions = sessionMap.getOrDefault(event.getId(), List.of());
            vo.setEarliestSessionTime(sessions.stream()
                .map(EventSession::getSessionTime).min(LocalDateTime::compareTo).orElse(null));
            vo.setMinPrice(sessions.stream()
                .map(s -> sessionMinPrice.getOrDefault(s.getId(), BigDecimal.ZERO))
                .filter(p -> p.compareTo(BigDecimal.ZERO) > 0)
                .min(BigDecimal::compareTo).orElse(BigDecimal.ZERO));
            return vo;
        }).toList();
    }

    // ==================== 演出详情 ====================

    public EventDetailVO detail(Integer eventId) {
        Event event = eventMapper.selectById(eventId);
        if (event == null) throw new BizException(ResultCode.NOT_FOUND, "演出不存在");

        EventDetailVO vo = new EventDetailVO();
        vo.setId(event.getId());
        vo.setTitle(event.getTitle());
        vo.setDescription(event.getDescription());
        vo.setPosterUrl(event.getPosterUrl());
        vo.setDuration(event.getDuration());
        vo.setCategoryId(event.getCategoryId());

        Category category = categoryMapper.selectById(event.getCategoryId());
        vo.setCategoryName(category != null ? category.getName() : "");

        Venue venue = venueMapper.selectById(event.getVenueId());
        if (venue != null) {
            VenueInfoVO vvo = new VenueInfoVO();
            vvo.setId(venue.getId());
            vvo.setName(venue.getName());
            vvo.setCity(venue.getCity());
            vvo.setAddress(venue.getAddress());
            vo.setVenue(vvo);
        }

        List<EventSession> sessions = sessionMapper.selectList(
            new LambdaQueryWrapper<EventSession>()
                .eq(EventSession::getEventId, eventId)
                .orderByAsc(EventSession::getSessionTime));

        List<SessionVO> sessionVOs = new ArrayList<>();
        for (EventSession session : sessions) {
            SessionVO svo = new SessionVO();
            svo.setId(session.getId());
            svo.setSessionTime(session.getSessionTime());
            svo.setStatus(session.getStatus());
            svo.setZones(buildZoneSummary(session.getId()));
            sessionVOs.add(svo);
        }
        vo.setSessions(sessionVOs);
        return vo;
    }

    private List<ZoneVO> buildZoneSummary(Integer sessionId) {
        List<Seat> seats = seatMapper.selectList(
            new LambdaQueryWrapper<Seat>().eq(Seat::getSessionId, sessionId));
        return seats.stream()
            .collect(Collectors.groupingBy(Seat::getZoneName)).entrySet().stream()
            .map(entry -> {
                ZoneVO zvo = new ZoneVO();
                zvo.setName(entry.getKey());
                zvo.setPrice(entry.getValue().get(0).getPrice());
                zvo.setTotalSeats((long) entry.getValue().size());
                zvo.setAvailableSeats(entry.getValue().stream()
                    .filter(s -> "available".equals(s.getStatus())).count());
                return zvo;
            }).toList();
    }

    // ==================== 座位图 ====================

    public List<SeatZoneVO> getSeatMap(Integer sessionId) {
        if (sessionMapper.selectById(sessionId) == null) {
            throw new BizException(ResultCode.NOT_FOUND, "场次不存在");
        }
        List<Seat> seats = seatMapper.selectList(
            new LambdaQueryWrapper<Seat>().eq(Seat::getSessionId, sessionId));

        Map<String, List<Seat>> grouped = seats.stream()
            .collect(Collectors.groupingBy(Seat::getZoneName));

        List<SeatZoneVO> zones = new ArrayList<>();
        int ci = 0;
        for (var entry : grouped.entrySet()) {
            SeatZoneVO zvo = new SeatZoneVO();
            zvo.setName(entry.getKey());
            zvo.setPrice(entry.getValue().get(0).getPrice());
            zvo.setColor(ZONE_COLORS[ci++ % ZONE_COLORS.length]);
            zvo.setSeats(entry.getValue().stream().map(seat -> {
                SeatInfoVO svo = new SeatInfoVO();
                svo.setId(seat.getId());
                svo.setZoneName(seat.getZoneName());
                svo.setSeatRow(seat.getSeatRow());
                svo.setSeatCol(seat.getSeatCol());
                svo.setPrice(seat.getPrice());
                svo.setStatus(seat.getStatus());
                return svo;
            }).toList());
            zones.add(zvo);
        }
        return zones;
    }

    // ==================== 锁定座位 ====================

    @Transactional
    public LocalDateTime lockSeats(Integer sessionId, List<Integer> seatIds, Integer userId) {
        LocalDateTime now = LocalDateTime.now();

        LambdaUpdateWrapper<Seat> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(Seat::getId, seatIds)
               .eq(Seat::getStatus, "available")
               .set(Seat::getStatus, "locked")
               .set(Seat::getLockTime, now)
               .set(Seat::getLockUserId, userId);
        int updated = seatMapper.update(null, wrapper);

        if (updated < seatIds.size()) {
            List<Seat> current = seatMapper.selectBatchIds(seatIds);
            if (current.isEmpty()) {
                throw new BizException(ResultCode.NOT_FOUND, "座位不存在");
            }
            boolean anySold = current.stream().anyMatch(s -> "sold".equals(s.getStatus()));
            if (anySold) {
                throw new BizException(ResultCode.SEAT_UNAVAILABLE);
            }
            throw new BizException(ResultCode.SEAT_LOCKED_BY_OTHER);
        }

        log.info("座位锁定: sessionId={}, seatIds={}, userId={}", sessionId, seatIds, userId);
        return now.plusMinutes(15);
    }

    // ==================== 释放座位 ====================

    @Transactional
    public void releaseSeats(Integer sessionId, List<Integer> seatIds, Integer userId) {
        LambdaUpdateWrapper<Seat> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(Seat::getId, seatIds)
               .eq(Seat::getStatus, "locked")
               .eq(Seat::getLockUserId, userId)
               .set(Seat::getStatus, "available")
               .set(Seat::getLockTime, null)
               .set(Seat::getLockUserId, null);
        seatMapper.update(null, wrapper);
        log.info("座位释放: sessionId={}, seatIds={}, userId={}", sessionId, seatIds, userId);
    }
}
