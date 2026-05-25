package com.showtime.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SessionVO {
    private Integer id;
    private LocalDateTime sessionTime;
    private Integer status;
    private List<ZoneVO> zones;
}
