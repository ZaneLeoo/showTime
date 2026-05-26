package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "座位（行锁核心表）")
@Data
@TableName("seats")
public class Seat {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "所属场次")
    private Integer sessionId;

    @Schema(description = "座位区域（VIP/A/B/C）")
    private String zoneName;

    @Schema(description = "排号")
    private String seatRow;

    @Schema(description = "列号")
    private Integer seatCol;

    @Schema(description = "该座位售价")
    private BigDecimal price;

    @Schema(description = "座位状态：available=可用 locked=已锁定 sold=已售")
    private String status;

    @Schema(description = "锁定时间（用于超时释放）")
    private LocalDateTime lockTime;

    @Schema(description = "锁定用户ID")
    private Integer lockUserId;
}
