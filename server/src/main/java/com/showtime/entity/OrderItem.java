package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "订单明细（票）")
@Data
@TableName("order_items")
public class OrderItem {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "所属订单")
    private Integer orderId;

    @Schema(description = "座位ID")
    private Integer seatId;

    @Schema(description = "实际售价")
    private BigDecimal price;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
