package com.showtime.vo;

import com.showtime.entity.User;
import lombok.Data;

@Data
public class UserInfoVO {

    private Integer id;
    private String phone;
    private String email;
    private String nickname;
    private String avatarUrl;

    public static UserInfoVO fromEntity(User user) {
        UserInfoVO vo = new UserInfoVO();
        vo.setId(user.getId());
        vo.setPhone(user.getPhone());
        vo.setEmail(user.getEmail());
        vo.setNickname(user.getNickname());
        vo.setAvatarUrl(user.getAvatarUrl());
        return vo;
    }
}
