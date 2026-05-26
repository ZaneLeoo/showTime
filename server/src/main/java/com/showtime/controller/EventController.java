package com.showtime.controller;

import com.showtime.common.PageResult;
import com.showtime.common.Result;
import com.showtime.dto.SeatLockRequest;
import com.showtime.dto.SeatLockResponse;
import com.showtime.dto.SeatReleaseRequest;
import com.showtime.service.EventService;
import com.showtime.service.SessionService;
import com.showtime.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "演出", description = "演出浏览、场次、选座、锁座/释放")
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final SessionService sessionService;

    @Operation(summary = "演出列表（搜索/筛选/排序/分页）")
    @GetMapping("/list")
    public Result<PageResult<EventCardVO>> list(
            @Parameter(description = "分类ID") @RequestParam(required = false) Integer categoryId,
            @Parameter(description = "城市") @RequestParam(required = false) String city,
            @Parameter(description = "关键词搜索") @RequestParam(required = false) String keyword,
            @Parameter(description = "演出日期起（yyyy-MM-dd）") @RequestParam(required = false) String dateStart,
            @Parameter(description = "演出日期止（yyyy-MM-dd）") @RequestParam(required = false) String dateEnd,
            @Parameter(description = "排序方式：time｜price｜hot") @RequestParam(required = false) String sortBy,
            @Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "12") Integer pageSize) {
        return Result.ok(eventService.list(categoryId, city, keyword,
            dateStart, dateEnd, sortBy, page, pageSize));
    }

    @Operation(summary = "演出详情（含场次与区域）")
    @GetMapping("/{id}")
    public Result<EventDetailVO> detail(
            @Parameter(description = "演出ID") @PathVariable Integer id) {
        return Result.ok(eventService.detail(id));
    }

    @Operation(summary = "场次座位图")
    @GetMapping("/session/{sessionId}/seats")
    public Result<List<SeatZoneVO>> seatMap(
            @Parameter(description = "场次ID") @PathVariable Integer sessionId) {
        return Result.ok(eventService.getSeatMap(sessionId));
    }

    @Operation(summary = "锁定座位（下单前）")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/seat/lock")
    public Result<SeatLockResponse> lockSeats(@Valid @RequestBody SeatLockRequest req,
                                              HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        return Result.ok(new SeatLockResponse(
            eventService.lockSeats(req.getSessionId(), req.getSeatIds(), user.getId())));
    }

    @Operation(summary = "释放座位（取消选座/超时）")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/seat/release")
    public Result<Void> releaseSeats(@Valid @RequestBody SeatReleaseRequest req,
                                     HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        eventService.releaseSeats(req.getSessionId(), req.getSeatIds(), user.getId());
        return Result.ok();
    }
}
