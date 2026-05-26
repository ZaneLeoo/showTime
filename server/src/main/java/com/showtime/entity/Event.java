package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 演出 */
@Data
@TableName("events")
public class Event {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 演出名称 */
    private String title;

    /** 演出简介 */
    private String description;

    /** 海报图片URL */
    private String posterUrl;

    /** 演出时长（分钟） */
    private Integer duration;

    /** 所属分类 */
    private Integer categoryId;

    /** 演出场馆 */
    private Integer venueId;

    /** 状态：0=即将开售 1=在售 2=售罄 3=已结束 */
    private Integer status;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}
