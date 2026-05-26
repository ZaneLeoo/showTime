package com.showtime.interceptor;

import com.showtime.common.BizException;
import com.showtime.common.ResultCode;
import com.showtime.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    private final UserMapper userMapper;

    public AdminInterceptor(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        var user = userMapper.selectById(userId);
        if (user == null || !"admin".equals(user.getRole())) {
            throw new BizException(ResultCode.FORBIDDEN, "需要管理员权限");
        }
        return true;
    }
}
