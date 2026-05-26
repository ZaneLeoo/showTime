package com.showtime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "更新个人信息请求")
@Data
public class UpdateProfileRequest {

    @Schema(description = "昵称", example = "新昵称")
    private String nickname;

    @Schema(description = "邮箱", example = "new@example.com")
    private String email;

    @Schema(description = "头像URL")
    private String avatarUrl;
}
