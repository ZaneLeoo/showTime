package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("venues")
public class Venue {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String city;

    private String address;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
