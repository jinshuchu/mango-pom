package com.louis.mango.admin.service.impl;

import com.louis.mango.admin.dao.UserMapper;
import com.louis.mango.admin.model.User;
import com.louis.mango.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-29
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }
}
