package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("search_logs")
public class SearchLog {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户ID（未登录为NULL）")
    private Integer userId;

    @Schema(description = "搜索关键词")
    private String keyword;

    @Schema(description = "搜索结果数")
    private Integer resultCount;

    @Schema(description = "搜索时间")
    private LocalDateTime searchedAt;
}
