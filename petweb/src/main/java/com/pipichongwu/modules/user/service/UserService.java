package com.pipichongwu.modules.user.service;

import com.pipichongwu.modules.user.model.User;

import java.util.List;

/**
 * Created by dongao on 2018/1/27.
 */
public interface UserService {

    User getUser(Integer id);
    User selectByOpenId(String openid);
    void insert(User user);
    void updateUser(User user);
}
