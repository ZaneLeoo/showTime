package com.showtime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "场馆信息")
@Data
public class VenueInfoVO {

    @Schema(description = "场馆ID")
    private Integer id;

    @Schema(description = "场馆名称")
    private String name;

    @Schema(description = "所在城市")
    private String city;

    @Schema(description = "详细地址")
    private String address;
}
