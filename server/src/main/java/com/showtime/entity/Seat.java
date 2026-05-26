package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** 座位（行锁核心表） */
@Data
@TableName("seats")
public class Seat {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 所属场次 */
    private Integer sessionId;

    /** 座位区域（VIP/A/B/C） */
    private String zoneName;

    /** 排号 */
    private String seatRow;

    /** 列号 */
    private Integer seatCol;

    /** 该座位售价 */
    private BigDecimal price;

    /** 座位状态：available=可用 locked=已锁定 sold=已售 */
    private String status;

    /** 锁定时间（用于超时释放） */
    private LocalDateTime lockTime;

    /** 锁定用户ID */
    private Integer lockUserId;
}
