package com.showtime.controller;

import com.showtime.common.Result;
import com.showtime.service.AdminReportService;
import com.showtime.vo.AdminDashboardVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台-报表", description = "仪表盘 + 销售报表")
@RestController
@RequestMapping("/api/admin/report")
@RequiredArgsConstructor
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Operation(summary = "仪表盘数据")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/dashboard")
    public Result<AdminDashboardVO> dashboard() {
        return Result.ok(adminReportService.dashboard());
    }
}
