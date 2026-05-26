package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "演出创建/编辑请求")
@Data
public class AdminEventRequest {

    @Schema(description = "演出名称", example = "周杰伦2026巡回演唱会")
    @NotBlank
    private String title;

    @Schema(description = "演出简介")
    private String description;

    @Schema(description = "海报图片URL")
    private String posterUrl;

    @Schema(description = "演出时长（分钟）", example = "120")
    private Integer duration;

    @Schema(description = "所属分类ID")
    @NotNull
    private Integer categoryId;

    @Schema(description = "演出场馆ID")
    @NotNull
    private Integer venueId;

    @Schema(description = "状态：0=即将开售 1=在售 2=售罄 3=已结束")
    private Integer status;
}
