package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("event_sessions")
public class EventSession {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer eventId;

    private LocalDateTime sessionTime;

    private Integer status;

    private LocalDateTime createdAt;
}
