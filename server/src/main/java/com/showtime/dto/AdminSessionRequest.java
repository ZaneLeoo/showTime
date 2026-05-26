package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "场次创建/编辑请求")
@Data
public class AdminSessionRequest {

    @Schema(description = "开演时间")
    @NotNull
    private LocalDateTime sessionTime;

    @Schema(description = "状态：0=取消 1=正常 2=已结束")
    private Integer status;

    @Schema(description = "票档+座位配置")
    @NotEmpty
    private List<ZoneConfig> zones;

    @Data
    @Schema(description = "票档配置")
    public static class ZoneConfig {
        @Schema(description = "区域名称", example = "VIP区")
        @NotEmpty
        private String name;

        @Schema(description = "票价", example = "1680")
        @NotNull
        private BigDecimal price;

        @Schema(description = "排数", example = "3")
        @NotNull
        private Integer rows;

        @Schema(description = "每排座位数", example = "14")
        @NotNull
        private Integer cols;
    }
}
