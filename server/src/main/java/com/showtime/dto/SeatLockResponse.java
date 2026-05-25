package com.showtime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SeatLockResponse {
    private LocalDateTime lockExpireAt;
}
