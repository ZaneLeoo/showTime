package com.showtime.controller;

import com.showtime.common.PageResult;
import com.showtime.common.Result;
import com.showtime.dto.AdminVenueRequest;
import com.showtime.entity.Venue;
import com.showtime.service.AdminVenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台-场馆", description = "场馆CRUD")
@RestController
@RequestMapping("/api/admin/venue")
@RequiredArgsConstructor
public class AdminVenueController {

    private final AdminVenueService adminVenueService;

    @Operation(summary = "场馆列表")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/list")
    public Result<PageResult<Venue>> list(
            @Parameter(description = "搜索关键词") @RequestParam(required = false) String keyword,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页条数") @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.ok(adminVenueService.list(keyword, page, pageSize));
    }

    @Operation(summary = "创建场馆")
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public Result<Venue> create(@Valid @RequestBody AdminVenueRequest req) {
        return Result.ok(adminVenueService.create(req));
    }

    @Operation(summary = "编辑场馆")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public Result<Venue> update(@Parameter(description = "场馆ID") @PathVariable Integer id,
                                @Valid @RequestBody AdminVenueRequest req) {
        return Result.ok(adminVenueService.update(id, req));
    }

    @Operation(summary = "删除场馆")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "场馆ID") @PathVariable Integer id) {
        adminVenueService.delete(id);
        return Result.ok();
    }
}
