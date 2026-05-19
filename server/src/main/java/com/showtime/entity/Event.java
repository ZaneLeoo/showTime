package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("events")
public class Event {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private String posterUrl;

    private Integer duration;

    private Integer categoryId;

    private Integer venueId;

    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
