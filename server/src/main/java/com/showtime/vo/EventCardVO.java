package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "演出卡片（列表用）")
@Data
public class EventCardVO {

    @Schema(description = "演出ID")
    private Integer id;

    @Schema(description = "演出标题")
    private String title;

    @Schema(description = "海报图片URL")
    private String posterUrl;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "场馆名称")
    private String venueName;

    @Schema(description = "所在城市")
    private String city;

    @Schema(description = "状态：1=即将开售｜2=在售｜3=售罄｜4=已结束")
    private Integer status;

    @Schema(description = "最低票价")
    private BigDecimal minPrice;

    @Schema(description = "最早场次时间")
    private LocalDateTime earliestSessionTime;
}
