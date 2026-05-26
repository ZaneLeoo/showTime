package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "演出")
@Data
@TableName("events")
public class Event {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "演出名称")
    private String title;

    @Schema(description = "演出简介")
    private String description;

    @Schema(description = "海报图片URL")
    private String posterUrl;

    @Schema(description = "演出时长（分钟）")
    private Integer duration;

    @Schema(description = "所属分类")
    private Integer categoryId;

    @Schema(description = "演出场馆")
    private Integer venueId;

    @Schema(description = "状态：0=即将开售 1=在售 2=售罄 3=已结束")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
}
