package com.showtime.controller;

import com.showtime.common.PageResult;
import com.showtime.common.Result;
import com.showtime.dto.SeatLockRequest;
import com.showtime.dto.SeatLockResponse;
import com.showtime.dto.SeatReleaseRequest;
import com.showtime.service.EventService;
import com.showtime.service.SessionService;
import com.showtime.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final SessionService sessionService;

    @GetMapping("/list")
    public Result<PageResult<EventCardVO>> list(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String dateStart,
            @RequestParam(required = false) String dateEnd,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "12") Integer pageSize) {
        return Result.ok(eventService.list(categoryId, city, keyword,
            dateStart, dateEnd, sortBy, page, pageSize));
    }

    @GetMapping("/{id}")
    public Result<EventDetailVO> detail(@PathVariable Integer id) {
        return Result.ok(eventService.detail(id));
    }

    @GetMapping("/session/{sessionId}/seats")
    public Result<List<SeatZoneVO>> seatMap(@PathVariable Integer sessionId) {
        return Result.ok(eventService.getSeatMap(sessionId));
    }

    @PostMapping("/seat/lock")
    public Result<SeatLockResponse> lockSeats(@Valid @RequestBody SeatLockRequest req,
                                              HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        return Result.ok(new SeatLockResponse(
            eventService.lockSeats(req.getSessionId(), req.getSeatIds(), user.getId())));
    }

    @PostMapping("/seat/release")
    public Result<Void> releaseSeats(@Valid @RequestBody SeatReleaseRequest req,
                                     HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        eventService.releaseSeats(req.getSessionId(), req.getSeatIds(), user.getId());
        return Result.ok();
    }
}
