package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("payments")
public class Payment {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private String payMethod;

    private BigDecimal amount;

    private String status;

    private String tradeNo;

    private LocalDateTime paidAt;

    private LocalDateTime createdAt;
}
