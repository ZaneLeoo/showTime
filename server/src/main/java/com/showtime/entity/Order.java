package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "订单")
@Data
@TableName("orders")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "订单号（业务唯一标识）")
    private String orderNo;

    @Schema(description = "下单用户")
    private Integer userId;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "订单状态：pending=待支付 paid=已支付 cancelled=已取消 expired=已过期")
    private String status;

    @Schema(description = "票数")
    private Integer ticketCount;

    @Schema(description = "支付时间")
    private LocalDateTime paidAt;

    @Schema(description = "取消时间")
    private LocalDateTime cancelledAt;

    @Schema(description = "创建时间（下单时间）")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
}
