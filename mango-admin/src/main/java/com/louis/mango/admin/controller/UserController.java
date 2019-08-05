package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.User;
import com.louis.mango.admin.service.ITokenService;
import com.louis.mango.admin.service.UserService;
import com.louis.mango.admin.util.Const;
import com.louis.mango.admin.vo.LoginBean;
import com.louis.mango.core.http.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-08-01
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ITokenService iTokenService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // token存放在cookies中的时间：7天
    private static final Integer EXP_TOKEN_COOKIES_MINS = 60 * 60 * 24 * 7;


    @PostMapping(value = "/login")
    public HttpResult login(LoginBean loginBean, HttpServletResponse response){
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        User user = userService.findUserByName(username);
        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        if (!Objects.equals(password, user.getPassword())) {
            return HttpResult.error("密码不正确");
        }
        String token = buildToken(user);
        addTokenToCookies(response, token);
        return HttpResult.ok(user);
    }

    private String buildToken(User user) {
        String token = iTokenService.generate(String.valueOf(user.getId()), null);
//        iCacheService.tempSaveCreatedToken(token);
        return token;
    }

    private void addTokenToCookies(HttpServletResponse response, String token){
        Cookie cookie = new Cookie(Const.PARAM_SECURITY_TOKEN, token);
        cookie.setMaxAge(EXP_TOKEN_COOKIES_MINS);// 设置为7天
        response.addCookie(cookie);
        logger.info("add tooken to cookie : {}", token);
    }
}
