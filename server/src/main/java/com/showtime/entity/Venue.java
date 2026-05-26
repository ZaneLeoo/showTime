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

    /** 场馆名称 */
    private String name;

    /** 所在城市 */
    private String city;

    /** 详细地址 */
    private String address;

    /** 场馆介绍 */
    private String description;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}
