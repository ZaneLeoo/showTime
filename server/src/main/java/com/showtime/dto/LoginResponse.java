package com.showtime.dto;

import com.showtime.vo.UserInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "登录响应")
@Data
@AllArgsConstructor
public class LoginResponse {

    @Schema(description = "会话Token")
    private String token;

    @Schema(description = "用户信息")
    private UserInfoVO userInfo;
}
