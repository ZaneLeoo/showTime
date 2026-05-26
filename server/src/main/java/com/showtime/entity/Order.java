package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("orders")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 订单号（业务唯一标识） */
    private String orderNo;

    /** 下单用户 */
    private Integer userId;

    /** 订单总金额 */
    private BigDecimal totalAmount;

    /** 订单状态：pending=待支付 paid=已支付 cancelled=已取消 expired=已过期 */
    private String status;

    /** 票数 */
    private Integer ticketCount;

    /** 支付时间 */
    private LocalDateTime paidAt;

    /** 取消时间 */
    private LocalDateTime cancelledAt;

    /** 创建时间（下单时间） */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}
