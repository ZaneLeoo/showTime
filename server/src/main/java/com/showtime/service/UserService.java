package com.showtime.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.showtime.common.BizException;
import com.showtime.common.ResultCode;
import com.showtime.dto.LoginRequest;
import com.showtime.dto.LoginResponse;
import com.showtime.dto.RegisterRequest;
import com.showtime.dto.UpdateProfileRequest;
import com.showtime.entity.User;
import com.showtime.entity.UserSession;
import com.showtime.mapper.UserMapper;
import com.showtime.mapper.UserSessionMapper;
import com.showtime.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserSessionMapper sessionMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /** 注册 */
    @Transactional
    public void register(RegisterRequest req) {
        // 手机号唯一
        if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getPhone, req.getPhone())) > 0) {
            throw new BizException(ResultCode.PHONE_EXISTS);
        }
        // 邮箱唯一（如果填了）
        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getEmail, req.getEmail())) > 0) {
                throw new BizException(ResultCode.EMAIL_EXISTS);
            }
        }

        User user = new User();
        user.setPhone(req.getPhone());
        user.setPasswordHash(encoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setNickname(Optional.ofNullable(req.getNickname()).orElse("用户" + req.getPhone().substring(7)));
        userMapper.insert(user);
        log.info("新用户注册: phone={}", req.getPhone());
    }

    /** 登录 */
    @Transactional
    public LoginResponse login(LoginRequest req) {
        User user = userMapper.selectOne(
            new LambdaQueryWrapper<User>().eq(User::getPhone, req.getPhone())
        );
        if (user == null || !encoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new BizException(ResultCode.LOGIN_FAILED);
        }
        if (user.getStatus() == 0) {
            throw new BizException(ResultCode.FORBIDDEN, "账号已被禁用");
        }

        // 生成 token，写入 session 表（Memory 引擎）
        String token = UUID.randomUUID().toString().replace("-", "");
        UserSession session = new UserSession();
        session.setUserId(user.getId());
        session.setToken(token);
        session.setExpiresAt(LocalDateTime.now().plusDays(7));
        sessionMapper.insert(session);

        log.info("用户登录成功: userId={}", user.getId());
        return new LoginResponse(token, UserInfoVO.fromEntity(user));
    }

    /** 根据 token 获取用户信息 */
    public UserInfoVO getUserInfo(String token) {
        User user = getUserByToken(token);
        return UserInfoVO.fromEntity(user);
    }

    /** 更新个人信息 */
    @Transactional
    public void updateProfile(Integer userId, UpdateProfileRequest req) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BizException(ResultCode.NOT_FOUND, "用户不存在");
        }
        if (req.getNickname() != null) user.setNickname(req.getNickname());
        if (req.getEmail() != null) {
            // 检查邮箱是否被他人占用
            Integer exist = userMapper.selectCount(
                new LambdaQueryWrapper<User>().eq(User::getEmail, req.getEmail()).ne(User::getId, userId)
            );
            if (exist > 0) {
                throw new BizException(ResultCode.EMAIL_EXISTS);
            }
            user.setEmail(req.getEmail());
        }
        if (req.getAvatarUrl() != null) user.setAvatarUrl(req.getAvatarUrl());
        userMapper.updateById(user);
    }

    /** 从 token 解析用户（供拦截器或其他 Service 使用） */
    public User getUserByToken(String token) {
        UserSession session = sessionMapper.selectOne(
            new LambdaQueryWrapper<UserSession>().eq(UserSession::getToken, token)
        );
        if (session == null || session.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        User user = userMapper.selectById(session.getUserId());
        if (user == null || user.getStatus() == 0) {
            throw new BizException(ResultCode.UNAUTHORIZED);
        }
        return user;
    }
}
