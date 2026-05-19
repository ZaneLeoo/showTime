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

    private String orderNo;

    private Integer userId;

    private BigDecimal totalAmount;

    private String status;

    private Integer ticketCount;

    private LocalDateTime paidAt;

    private LocalDateTime cancelledAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
