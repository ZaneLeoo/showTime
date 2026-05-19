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

    private Integer userId;

    private String keyword;

    private Integer resultCount;

    private LocalDateTime searchedAt;
}
