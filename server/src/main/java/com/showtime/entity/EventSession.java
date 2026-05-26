package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "演出场次")
@Data
@TableName("event_sessions")
public class EventSession {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "所属演出")
    private Integer eventId;

    @Schema(description = "开演时间")
    private LocalDateTime sessionTime;

    @Schema(description = "状态：0=取消 1=正常 2=已结束")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
