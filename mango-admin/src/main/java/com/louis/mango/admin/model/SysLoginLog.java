package com.louis.mango.admin.model;

import lombok.Data;

@Data
public class SysLoginLog extends BaseModel {

	public static final String STATUS_LOGIN = "login";
	public static final String STATUS_LOGOUT = "logout";
	public static final String STATUS_ONLINE = "online";
	
    private String userName;

    private String status;

    private String ip;

}