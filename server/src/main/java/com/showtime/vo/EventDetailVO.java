package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "演出详情")
@Data
public class EventDetailVO {

    @Schema(description = "演出ID")
    private Integer id;

    @Schema(description = "演出标题")
    private String title;

    @Schema(description = "演出描述")
    private String description;

    @Schema(description = "海报图片URL")
    private String posterUrl;

    @Schema(description = "时长（分钟）")
    private Integer duration;

    @Schema(description = "分类ID")
    private Integer categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "场馆信息")
    private VenueInfoVO venue;

    @Schema(description = "场次列表")
    private List<SessionVO> sessions;
}
