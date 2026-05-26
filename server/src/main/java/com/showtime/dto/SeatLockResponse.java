package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "锁座响应")
@Data
@AllArgsConstructor
public class SeatLockResponse {

    @Schema(description = "锁座过期时间（需在此时间前完成支付）")
    private LocalDateTime lockExpireAt;
}
