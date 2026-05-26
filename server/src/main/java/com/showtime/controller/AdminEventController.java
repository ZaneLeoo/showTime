package com.showtime.controller;

import com.showtime.common.PageResult;
import com.showtime.common.Result;
import com.showtime.dto.AdminEventRequest;
import com.showtime.entity.Event;
import com.showtime.service.AdminEventService;
import com.showtime.vo.AdminEventVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台-演出", description = "演出CRUD")
@RestController
@RequestMapping("/api/admin/event")
@RequiredArgsConstructor
public class AdminEventController {

    private final AdminEventService adminEventService;

    @Operation(summary = "演出列表")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/list")
    public Result<PageResult<AdminEventVO>> list(
            @Parameter(description = "搜索关键词") @RequestParam(required = false) String keyword,
            @Parameter(description = "状态筛选") @RequestParam(required = false) Integer status,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.ok(adminEventService.list(keyword, status, page, pageSize));
    }

    @Operation(summary = "创建演出")
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public Result<Event> create(@Valid @RequestBody AdminEventRequest req) {
        return Result.ok(adminEventService.create(req));
    }

    @Operation(summary = "编辑演出")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public Result<Event> update(@Parameter(description = "演出ID") @PathVariable Integer id,
                                @Valid @RequestBody AdminEventRequest req) {
        return Result.ok(adminEventService.update(id, req));
    }

    @Operation(summary = "删除演出")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "演出ID") @PathVariable Integer id) {
        adminEventService.delete(id);
        return Result.ok();
    }
}
