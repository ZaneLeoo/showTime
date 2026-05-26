package com.showtime.controller;

import com.showtime.common.PageResult;
import com.showtime.common.Result;
import com.showtime.dto.CreateOrderRequest;
import com.showtime.service.OrderService;
import com.showtime.service.SessionService;
import com.showtime.vo.OrderCardVO;
import com.showtime.vo.OrderDetailVO;
import com.showtime.vo.OrderPreviewVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单", description = "下单、支付、取消、订单列表/详情")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SessionService sessionService;

    @Operation(summary = "订单预览（确认页数据）")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/preview")
    public Result<OrderPreviewVO> preview(
            @Parameter(description = "场次ID") @RequestParam Integer sessionId,
            @Parameter(description = "座位ID列表，逗号分隔") @RequestParam String seatIds,
            HttpServletRequest request) {
        if (seatIds == null || seatIds.isBlank()) {
            return Result.fail(com.showtime.common.ResultCode.BAD_REQUEST, "座位ID不能为空");
        }
        var user = sessionService.requireLogin(request);
        var ids = java.util.Arrays.stream(seatIds.split(","))
            .map(Integer::parseInt).toList();
        return Result.ok(orderService.preview(sessionId, ids, user.getId()));
    }

    @Operation(summary = "创建订单")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/create")
    public Result<OrderService.CreateOrderResult> createOrder(@Valid @RequestBody CreateOrderRequest req,
                                                               HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        return Result.ok(orderService.createOrder(req.getSessionId(), req.getSeatIds(), user.getId()));
    }

    @Operation(summary = "支付订单")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/{id}/pay")
    public Result<Void> payOrder(
            @Parameter(description = "订单ID") @PathVariable Integer id,
            HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        orderService.payOrder(id, user.getId());
        return Result.ok();
    }

    @Operation(summary = "取消订单")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/{id}/cancel")
    public Result<Void> cancelOrder(
            @Parameter(description = "订单ID") @PathVariable Integer id,
            HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        orderService.cancelOrder(id, user.getId());
        return Result.ok();
    }

    @Operation(summary = "我的票夹（订单列表）")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/list")
    public Result<PageResult<OrderCardVO>> list(
            @Parameter(description = "订单状态：pending｜paid｜cancelled，不传=全部")
            @RequestParam(required = false) String status,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize,
            HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        return Result.ok(orderService.list(user.getId(), status, page, pageSize));
    }

    @Operation(summary = "订单详情")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public Result<OrderDetailVO> detail(
            @Parameter(description = "订单ID") @PathVariable Integer id,
            HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        return Result.ok(orderService.detail(id, user.getId()));
    }
}
