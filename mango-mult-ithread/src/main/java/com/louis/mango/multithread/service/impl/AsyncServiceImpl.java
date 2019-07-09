package com.louis.mango.multithread.service.impl;

import com.louis.mango.multithread.config.ExecutorConfig;
import com.louis.mango.multithread.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-08
 */

@Service
public class AsyncServiceImpl implements AsyncService {

    private static final Logger log = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Autowired
    private ExecutorConfig executorConfig;

    @Async("asyncServiceExecutor")
    @Override
    public void asyncMethod() {

        log.info("异步任务日志111111...");
    }

    @Async("asyncServiceExecutor")
    @Override
    public void asyncMethod2() {

        log.info("异步任务日志222222");
    }

    @Override
    public Future<String> asyncMethodWithReturn() {
        log.info("异步任务有返回结果");
        return new AsyncResult<>("Task Completed");
    }

}