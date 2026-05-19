package com.showtime.dto;

import com.showtime.vo.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private UserInfoVO userInfo;
}
