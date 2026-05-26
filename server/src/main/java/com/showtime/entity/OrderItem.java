package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** 订单明细（票） */
@Data
@TableName("order_items")
public class OrderItem {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 所属订单 */
    private Integer orderId;

    /** 座位ID */
    private Integer seatId;

    /** 实际售价 */
    private BigDecimal price;

    /** 创建时间 */
    private LocalDateTime createdAt;
}
