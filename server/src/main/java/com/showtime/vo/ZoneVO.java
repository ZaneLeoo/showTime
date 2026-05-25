package com.showtime.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ZoneVO {
    private String name;
    private BigDecimal price;
    private Long totalSeats;
    private Long availableSeats;
}
