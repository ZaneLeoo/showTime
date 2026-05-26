package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "订单卡片（列表用）")
@Data
public class OrderCardVO {

    @Schema(description = "订单ID")
    private Integer id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "演出标题")
    private String eventTitle;

    @Schema(description = "海报URL")
    private String posterUrl;

    @Schema(description = "场次时间")
    private String sessionTime;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "票数")
    private Integer ticketCount;

    @Schema(description = "订单状态：pending｜paid｜cancelled")
    private String status;

    @Schema(description = "下单时间")
    private LocalDateTime createdAt;
}
