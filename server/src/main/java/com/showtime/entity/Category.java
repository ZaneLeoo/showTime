package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/** 演出分类 */
@Data
@TableName("categories")
public class Category {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 分类名称 */
    private String name;

    /** 父分类ID（自关联，支持二级分类） */
    private Integer parentId;

    /** 排序号 */
    private Integer sortOrder;

    /** 创建时间 */
    private LocalDateTime createdAt;
}
