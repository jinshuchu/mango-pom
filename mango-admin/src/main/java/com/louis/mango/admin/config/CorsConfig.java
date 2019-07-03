package com.louis.mango.admin.config;

import com.louis.mango.admin.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-06-23
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")	// 允许跨域访问的路径
                .allowedOrigins("*")	// 允许跨域访问的源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")	// 允许请求方法
                .maxAge(168000)	// 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true);	// 是否发送cookie
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .excludePathPatterns("/swagger-ui.html/**", "/webjars/**", "/swagger-resources/**", "/", "/error")
                .order(1);
    }
}
