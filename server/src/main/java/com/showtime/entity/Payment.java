package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** 支付记录 */
@Data
@TableName("payments")
public class Payment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 所属订单 */
    private Integer orderId;

    /** 支付方式 */
    private String payMethod;

    /** 支付金额 */
    private BigDecimal amount;

    /** 支付状态：pending=待支付 success=成功 failed=失败 */
    private String status;

    /** 第三方交易号 */
    private String tradeNo;

    /** 支付时间 */
    private LocalDateTime paidAt;

    /** 创建时间 */
    private LocalDateTime createdAt;
}
