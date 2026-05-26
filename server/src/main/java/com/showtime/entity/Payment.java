package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("payments")
public class Payment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "所属订单")
    private Integer orderId;

    @Schema(description = "支付方式")
    private String payMethod;

    @Schema(description = "支付金额")
    private BigDecimal amount;

    @Schema(description = "支付状态：pending=待支付 success=成功 failed=失败")
    private String status;

    @Schema(description = "第三方交易号")
    private String tradeNo;

    @Schema(description = "支付时间")
    private LocalDateTime paidAt;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
