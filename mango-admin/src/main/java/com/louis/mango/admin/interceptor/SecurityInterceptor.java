package com.louis.mango.admin.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-07-02
 */

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 输出请求url信息 **/
        logger.info("{}", request.getRequestURI() + (request.getQueryString() != null ? request.getQueryString() : ""));
        return true;
    }
}

