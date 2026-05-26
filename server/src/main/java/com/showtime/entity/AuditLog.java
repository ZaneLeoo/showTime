package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("audit_log")
public class AuditLog {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "变更表名")
    private String tableName;

    @Schema(description = "变更记录ID")
    private Integer recordId;

    @Schema(description = "变更字段名")
    private String fieldName;

    @Schema(description = "变更前值")
    private String oldValue;

    @Schema(description = "变更后值")
    private String newValue;

    @Schema(description = "变更时间")
    private LocalDateTime changedAt;
}
