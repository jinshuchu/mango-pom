package com.louis.mango.admin.service;

import com.louis.mango.admin.model.User;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-29
 */
public interface UserService {

    User findUserByName(String name);

    int save(User user);
}
