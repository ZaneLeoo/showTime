package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "场馆")
@Data
@TableName("venues")
public class Venue {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "场馆名称")
    private String name;

    @Schema(description = "所在城市")
    private String city;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "场馆介绍")
    private String description;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
}
