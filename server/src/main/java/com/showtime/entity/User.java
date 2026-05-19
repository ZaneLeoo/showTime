package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("users")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String phone;

    private String email;

    private String passwordHash;

    private String nickname;

    private String avatarUrl;

    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
