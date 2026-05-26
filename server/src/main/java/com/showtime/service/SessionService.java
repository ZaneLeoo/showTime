package com.showtime.service;

import com.showtime.common.BizException;
import com.showtime.common.ResultCode;
import com.showtime.entity.User;
import com.showtime.entity.UserSession;
import com.showtime.mapper.UserMapper;
import com.showtime.mapper.UserSessionMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 会话工具 — 从请求头中提取 token 并解析当前用户
 */
@Component
@RequiredArgsConstructor
public class SessionService {

    private final UserSessionMapper sessionMapper;
    private final UserMapper userMapper;

    /** 从请求中获取当前登录用户，未登录则抛异常 */
    public User requireLogin(HttpServletRequest request) {
        String token = extractToken(request);
        UserSession session = sessionMapper.selectOne(
            new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<UserSession>()
                .eq(UserSession::getToken, token)
        );
        if (session == null || session.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        User user = userMapper.selectById(session.getUserId());
        if (user == null || user.getStatus() == 0) {
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        request.setAttribute("userId", user.getId());
        return user;
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        throw new BizException(ResultCode.UNAUTHORIZED);
    }
}
