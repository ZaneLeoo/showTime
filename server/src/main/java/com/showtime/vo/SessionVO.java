package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "场次信息（含区域汇总）")
@Data
public class SessionVO {

    @Schema(description = "场次ID")
    private Integer id;

    @Schema(description = "场次时间")
    private LocalDateTime sessionTime;

    @Schema(description = "状态：1=在售｜2=售罄｜3=已结束")
    private Integer status;

    @Schema(description = "区域汇总列表")
    private List<ZoneVO> zones;
}
