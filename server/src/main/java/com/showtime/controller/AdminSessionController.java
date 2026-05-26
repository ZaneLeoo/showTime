package com.showtime.controller;

import com.showtime.common.Result;
import com.showtime.dto.AdminSessionRequest;
import com.showtime.entity.EventSession;
import com.showtime.service.AdminSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "管理后台-场次", description = "场次CRUD + 座位生成")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminSessionController {

    private final AdminSessionService adminSessionService;

    @Operation(summary = "某演出的场次列表")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/event/{eventId}/session/list")
    public Result<List<EventSession>> list(@Parameter(description = "演出ID") @PathVariable Integer eventId) {
        return Result.ok(adminSessionService.listByEvent(eventId));
    }

    @Operation(summary = "创建场次（含座位生成）")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/event/{eventId}/session")
    public Result<EventSession> create(@Parameter(description = "演出ID") @PathVariable Integer eventId,
                                       @Valid @RequestBody AdminSessionRequest req) {
        return Result.ok(adminSessionService.create(eventId, req));
    }

    @Operation(summary = "编辑场次")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/session/{id}")
    public Result<EventSession> update(@Parameter(description = "场次ID") @PathVariable Integer id,
                                       @Valid @RequestBody AdminSessionRequest req) {
        return Result.ok(adminSessionService.update(id, req));
    }

    @Operation(summary = "删除场次（级联删除座位）")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/session/{id}")
    public Result<Void> delete(@Parameter(description = "场次ID") @PathVariable Integer id) {
        adminSessionService.delete(id);
        return Result.ok();
    }
}
