package com.louis.mango.admin.interceptor;

import com.louis.mango.admin.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 拦截请求接口，记录http请求日志
 * @Author: created by wangkaishuang on 2019-07-03
 */

@Component
public class HttpLogInterceptor extends HandlerInterceptorAdapter{

    /**
     * 用于记录每次调用的开始时间点，最后用于记录请求耗时
     */
    private static final Logger logger = LoggerFactory.getLogger(HttpLogInterceptor.class);

    private static final String REQUEST_PATH = "path";
    private static final String REQUEST_METHOD = "method";
    private static final String REQUEST_COOKIE = "cookie";
    private static final String REQUEST_START_TIME = "start_time";
    private static final String ATTRIBUTE_NAME = "request_log";

    /**
     * 请求调用之前触发
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> map = new HashMap<>();

        String requestPath = request.getRequestURI();
        StringBuilder requestParam = new StringBuilder();
        StringBuilder requestCookie = new StringBuilder();
        String method = request.getMethod();

        try {
            Map<String, String[]> paramMap = request.getParameterMap();
            if (paramMap != null && paramMap.size() > 0) {
                paramMap.forEach((k, v) -> requestParam.append(k).append("=").append(request.getParameter(k)).append("&"));
                requestParam.delete(requestParam.length() - 1, requestParam.length());
                requestPath = requestPath + "?" + requestParam;
            }

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    requestCookie.append(c.getName()).append("=").append(c.getValue()).append(";");
                }
            }

            map.put(REQUEST_PATH, requestPath);
            map.put(REQUEST_METHOD, method);
            map.put(REQUEST_COOKIE, requestCookie.toString());
            map.put(REQUEST_START_TIME, "" + System.currentTimeMillis());

            request.setAttribute(ATTRIBUTE_NAME, map);
        } catch (Exception e) {
            logger.error("Try to read from request failed", e);
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * 请求处理完成，视图渲染之前触发
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        doLog(request, response);
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 日志输出到指定文件中
     * @param request
     * @param response
     */
    private void doLog(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, String> rm = (Map<String, String>)request.getAttribute(ATTRIBUTE_NAME);

            long start = Long.valueOf(rm.get(REQUEST_START_TIME)).longValue();
            long timeSpend = System.currentTimeMillis() - start;

            // 请求时间点
            LocalDateTime requestTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault());

            SysUser user = request.getAttribute("sysuser") == null ? null : (SysUser) request.getAttribute("sysuser");
            Long userId = user == null ? 0L : user.getId();

            // 格式：时间 | method | URL | cookies | userId | time-spend
            logger.info("{} | {} | {} | {} | {} | {}", requestTime.toString(), rm.get(REQUEST_METHOD), timeSpend, rm.get(REQUEST_PATH), rm.get(REQUEST_COOKIE), userId);
        } catch (Exception e) {
            logger.error("Try to write from request failed", e);
        }finally {
            request.removeAttribute(ATTRIBUTE_NAME);
        }
    }
}
