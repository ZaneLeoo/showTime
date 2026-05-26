package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_sessions")
public class UserSession {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 会话令牌 */
    private String token;

    /** 过期时间 */
    private LocalDateTime expiresAt;

    /** 创建时间 */
    private LocalDateTime createdAt;
}
