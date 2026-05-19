package com.showtime.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("seats")
public class Seat {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer sessionId;

    private String zoneName;

    private String seatRow;

    private Integer seatCol;

    private BigDecimal price;

    private String status;

    private LocalDateTime lockTime;

    private Integer lockUserId;
}
