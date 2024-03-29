package com.louis.mango.backup.util;

/**
<<<<<<< HEAD
 * @Description: http结果封装
 * @Author: created by wangkaishuang on 2019-06-23
=======
 * HTTP结果封装
 * @author Louis
 * @date Jan 15, 2019
>>>>>>> 13dbe0d8b2ddd1fc19ee76cd352c8c23e43f3787
 */
public class HttpResult {

	private int code = 200;
	private String msg;
	private Object data;
	
	public static HttpResult error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static HttpResult error(String msg) {
		return error(500, msg);
	}
	
	public static HttpResult error(int code, String msg) {
		HttpResult r = new HttpResult();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static HttpResult ok(String msg) {
		HttpResult r = new HttpResult();
		r.setMsg(msg);
		return r;
	}
	
	public static HttpResult ok(Object data) {
		HttpResult r = new HttpResult();
		r.setData(data);
		return r;
	}
	
	public static HttpResult ok() {
		return new HttpResult();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
