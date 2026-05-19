package com.showtime.controller;

import com.showtime.common.Result;
import com.showtime.dto.LoginRequest;
import com.showtime.dto.LoginResponse;
import com.showtime.dto.RegisterRequest;
import com.showtime.dto.UpdateProfileRequest;
import com.showtime.service.SessionService;
import com.showtime.service.UserService;
import com.showtime.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    /** 注册 */
    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody RegisterRequest req) {
        userService.register(req);
        return Result.ok();
    }

    /** 登录 */
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        return Result.ok(userService.login(req));
    }

    /** 获取当前用户信息 */
    @GetMapping("/info")
    public Result<UserInfoVO> info(HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        return Result.ok(UserInfoVO.fromEntity(user));
    }

    /** 更新个人信息 */
    @PutMapping("/profile")
    public Result<Void> updateProfile(@Valid @RequestBody UpdateProfileRequest req,
                                       HttpServletRequest request) {
        var user = sessionService.requireLogin(request);
        userService.updateProfile(user.getId(), req);
        return Result.ok();
    }
}
