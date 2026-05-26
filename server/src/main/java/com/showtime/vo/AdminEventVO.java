package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Schema(description = "管理后台演出列表VO")
@Data
public class AdminEventVO {
    @Schema(description = "演出ID")
    private Integer id;
    @Schema(description = "演出名称")
    private String title;
    @Schema(description = "海报URL")
    private String posterUrl;
    @Schema(description = "演出时长")
    private Integer duration;
    @Schema(description = "分类名称")
    private String categoryName;
    @Schema(description = "场馆名称")
    private String venueName;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
