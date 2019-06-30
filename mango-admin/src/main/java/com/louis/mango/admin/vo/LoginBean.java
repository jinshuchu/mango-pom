package com.louis.mango.admin.vo;

import lombok.Data;

/**
 * @Description: 登陆接口封装对象
 * @Author: created by wangkaishuang on 2019-06-23
 */

@Data
public class LoginBean {

	private String account;
	private String password;
	private String captcha;
	
}
