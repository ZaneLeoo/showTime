package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "创建订单请求")
@Data
public class CreateOrderRequest {

    @Schema(description = "场次ID", example = "1")
    @NotNull
    private Integer sessionId;

    @Schema(description = "座位ID列表", example = "[101, 102]")
    @NotEmpty
    private List<Integer> seatIds;
}
