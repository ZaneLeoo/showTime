package com.showtime.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SeatLockRequest {
    @NotNull
    private Integer sessionId;

    @NotEmpty
    private List<Integer> seatIds;
}
