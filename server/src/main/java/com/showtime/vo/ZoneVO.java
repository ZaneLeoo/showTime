package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "区域汇总（不含具体座位）")
@Data
public class ZoneVO {

    @Schema(description = "区域名称", example = "VIP区")
    private String name;

    @Schema(description = "该区域票价")
    private BigDecimal price;

    @Schema(description = "总座位数")
    private Long totalSeats;

    @Schema(description = "可用座位数")
    private Long availableSeats;
}
