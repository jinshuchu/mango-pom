package com.louis.mango.multithread.service;

import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-08
 */
public interface AsyncService {

    void asyncMethod();

    void asyncMethod2();

    Future<String> asyncMethodWithReturn();
}
