package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "座位信息")
@Data
public class SeatInfoVO {

    @Schema(description = "座位ID")
    private Integer id;

    @Schema(description = "区域名称")
    private String zoneName;

    @Schema(description = "排号")
    private String seatRow;

    @Schema(description = "列号")
    private Integer seatCol;

    @Schema(description = "票价")
    private BigDecimal price;

    @Schema(description = "状态：available｜sold｜locked")
    private String status;
}
