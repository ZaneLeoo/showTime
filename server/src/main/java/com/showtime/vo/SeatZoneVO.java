package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "座位区域（含座位列表）")
@Data
public class SeatZoneVO {

    @Schema(description = "区域名称", example = "VIP区")
    private String name;

    @Schema(description = "该区域票价")
    private BigDecimal price;

    @Schema(description = "区域颜色标识")
    private String color;

    @Schema(description = "该区域下的所有座位")
    private List<SeatInfoVO> seats;
}
