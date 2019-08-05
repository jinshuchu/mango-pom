package com.louis.mango.admin.service;

import com.louis.mango.admin.vo.Payload;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-08-01
 */
public interface ITokenService {

    String generate(String userId, String userVerify, int expires);

    String generate(String userId, String userVerify);

    /**
     * @param token
     * @return
     */
    Payload decode(String token);

    /**
     * @param payload
     * @return
     */
    boolean isValid(Payload payload);

}
