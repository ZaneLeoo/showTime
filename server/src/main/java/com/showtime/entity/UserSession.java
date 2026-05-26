package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_sessions")
public class UserSession {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "会话令牌")
    private String token;

    @Schema(description = "过期时间")
    private LocalDateTime expiresAt;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
