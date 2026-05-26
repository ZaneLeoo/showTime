package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "场馆创建/编辑请求")
@Data
public class AdminVenueRequest {

    @Schema(description = "场馆名称", example = "北京鸟巢")
    @NotBlank
    private String name;

    @Schema(description = "所在城市", example = "北京")
    @NotBlank
    private String city;

    @Schema(description = "详细地址")
    @NotBlank
    private String address;

    @Schema(description = "场馆介绍")
    private String description;
}
