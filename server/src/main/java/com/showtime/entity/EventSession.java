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

    /** 所属演出 */
    private Integer eventId;

    /** 开演时间 */
    private LocalDateTime sessionTime;

    /** 状态：0=取消 1=正常 2=已结束 */
    private Integer status;

    /** 创建时间 */
    private LocalDateTime createdAt;
}
