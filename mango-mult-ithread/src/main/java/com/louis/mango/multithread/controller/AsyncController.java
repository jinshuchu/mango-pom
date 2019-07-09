package com.louis.mango.multithread.controller;

import com.louis.mango.multithread.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-08
 */

@RestController
public class AsyncController {

    private Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncService asyncService;

    @RequestMapping(value = "/printLog", method = RequestMethod.GET)
    public void printLog(){

        log.info("----------------------");

        log.info("调用printLog接口...");

        asyncService.asyncMethod2();

        asyncService.asyncMethod();

        log.info("----------------------");

        log.info("这个是tomcat线程");

    }
}
