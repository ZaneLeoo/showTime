package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "锁座请求")
@Data
public class SeatLockRequest {

    @Schema(description = "场次ID", example = "1")
    @NotNull
    private Integer sessionId;

    @Schema(description = "要锁定的座位ID列表", example = "[101, 102, 103]")
    @NotEmpty
    private List<Integer> seatIds;
}
