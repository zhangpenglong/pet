package com.pipichongwu.modules.user.dao;


import com.pipichongwu.modules.user.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by dongao on 2018/1/27.
 */
@Repository
public interface UserMapper {

    User selectById(Integer id);
    User selectByOpenId(String openid);
    void insert(User user);

    void updateUser(User user);

}
