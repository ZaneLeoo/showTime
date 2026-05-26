package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "释放座位请求")
@Data
public class SeatReleaseRequest {

    @Schema(description = "场次ID", example = "1")
    @NotNull
    private Integer sessionId;

    @Schema(description = "要释放的座位ID列表", example = "[101, 102]")
    @NotEmpty
    private List<Integer> seatIds;
}