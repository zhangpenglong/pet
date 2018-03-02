package com.pipichongwu.modules.user.service;

import com.pipichongwu.modules.user.dao.UserMapper;
import com.pipichongwu.modules.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongao on 2018/1/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByOpenId(String openid) {
        return userMapper.selectByOpenId(openid);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }
}
