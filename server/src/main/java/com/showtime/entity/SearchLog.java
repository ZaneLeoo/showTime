package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("search_logs")
public class SearchLog {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户ID（未登录为NULL） */
    private Integer userId;

    /** 搜索关键词 */
    private String keyword;

    /** 搜索结果数 */
    private Integer resultCount;

    /** 搜索时间 */
    private LocalDateTime searchedAt;
}
