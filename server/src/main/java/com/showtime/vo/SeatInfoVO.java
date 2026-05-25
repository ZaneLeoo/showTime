package com.showtime.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeatInfoVO {
    private Integer id;
    private String zoneName;
    private String seatRow;
    private Integer seatCol;
    private BigDecimal price;
    private String status;
}
