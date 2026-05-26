package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台仪表盘")
@Data
public class AdminDashboardVO {

    @Schema(description = "今日订单数")
    private long todayOrderCount;

    @Schema(description = "今日收入")
    private BigDecimal todayRevenue;

    @Schema(description = "今日新增用户")
    private long todayNewUsers;

    @Schema(description = "在售演出数")
    private long activeEventCount;

    @Schema(description = "近期订单")
    private List<RecentOrder> recentOrders;

    @Schema(description = "演出销量排行")
    private List<EventSalesRank> topEvents;

    @Data
    @Schema(description = "近期订单摘要")
    public static class RecentOrder {
        private String orderNo;
        private String eventTitle;
        private BigDecimal amount;
        private String status;
        private String createdAt;
    }

    @Data
    @Schema(description = "演出销量排行")
    public static class EventSalesRank {
        private String eventTitle;
        private long ticketCount;
        private BigDecimal revenue;
    }
}
