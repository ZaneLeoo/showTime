package com.showtime.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SeatZoneVO {
    private String name;
    private BigDecimal price;
    private String color;
    private List<SeatInfoVO> seats;
}
