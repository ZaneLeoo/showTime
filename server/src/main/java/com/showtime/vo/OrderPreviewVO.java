package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "订单预览（确认页渲染用）")
@Data
public class OrderPreviewVO {

    @Schema(description = "演出信息")
    private EventInfo eventInfo;

    @Schema(description = "选中座位明细")
    private List<SeatPreview> seats;

    @Schema(description = "合计金额")
    private BigDecimal totalPrice;

    @Schema(description = "座位数")
    private int seatCount;

    @Data
    @Schema(description = "预览中的演出信息")
    public static class EventInfo {
        @Schema(description = "演出标题")
        private String title;
        @Schema(description = "海报URL")
        private String posterUrl;
        @Schema(description = "场次时间")
        private String sessionTime;
        @Schema(description = "场馆名称")
        private String venueName;
    }

    @Data
    @Schema(description = "预览中的座位")
    public static class SeatPreview {
        @Schema(description = "座位ID")
        private Integer id;
        @Schema(description = "区域名称")
        private String zoneName;
        @Schema(description = "排号")
        private String seatRow;
        @Schema(description = "列号")
        private Integer seatCol;
        @Schema(description = "票价")
        private BigDecimal price;
    }
}
