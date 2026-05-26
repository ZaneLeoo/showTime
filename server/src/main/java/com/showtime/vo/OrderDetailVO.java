package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "订单详情")
@Data
public class OrderDetailVO {

    @Schema(description = "订单ID")
    private Integer id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "订单状态：pending｜paid｜cancelled")
    private String status;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "票数")
    private Integer ticketCount;

    @Schema(description = "支付时间")
    private LocalDateTime paidAt;

    @Schema(description = "取消时间")
    private LocalDateTime cancelledAt;

    @Schema(description = "下单时间")
    private LocalDateTime createdAt;

    @Schema(description = "演出信息")
    private EventInfo event;

    @Schema(description = "票列表")
    private List<TicketInfo> tickets;

    @Schema(description = "支付信息")
    private PaymentInfo payment;

    @Data
    @Schema(description = "订单详情中的演出信息")
    public static class EventInfo {
        @Schema(description = "演出ID")
        private Integer id;
        @Schema(description = "演出标题")
        private String title;
        @Schema(description = "海报URL")
        private String posterUrl;
        @Schema(description = "场馆名称")
        private String venueName;
        @Schema(description = "场次时间")
        private String sessionTime;
    }

    @Data
    @Schema(description = "票务明细")
    public static class TicketInfo {
        @Schema(description = "区域名称")
        private String zoneName;
        @Schema(description = "排号")
        private String seatRow;
        @Schema(description = "列号")
        private Integer seatCol;
        @Schema(description = "票价")
        private BigDecimal price;
    }

    @Data
    @Schema(description = "支付信息")
    public static class PaymentInfo {
        @Schema(description = "支付方式")
        private String payMethod;
        @Schema(description = "支付金额")
        private BigDecimal amount;
        @Schema(description = "支付状态")
        private String status;
        @Schema(description = "第三方交易号")
        private String tradeNo;
        @Schema(description = "支付时间")
        private LocalDateTime paidAt;
    }
}
