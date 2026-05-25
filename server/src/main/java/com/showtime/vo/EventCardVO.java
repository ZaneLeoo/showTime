package com.showtime.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventCardVO {
    private Integer id;
    private String title;
    private String posterUrl;
    private String categoryName;
    private String venueName;
    private String city;
    private Integer status;
    private BigDecimal minPrice;
    private LocalDateTime earliestSessionTime;
}
