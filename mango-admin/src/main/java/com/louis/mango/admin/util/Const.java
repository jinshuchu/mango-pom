package com.louis.mango.admin.util;

/**
 * 常用常量设置类
 * Created by wangkaishuang on 2018-05-10.
 */
public class Const {

    /** 与登录用户有关的常量 */
    public static final String PARAM_USER = "user";

    /** 主日历ID */
    public static final String USER_PRIMARY_CALENDER_ID = "user_primary_calendar_id";

    /** 与签名有关的常量,以下都是 */
    public static final String PARAM_SECURITY_TOKEN = "token";

    public static final String PARAM_SECURITY_SIGN = "sign";

    public static final String PARAM_SECURITY_CLIENT = "client";

    public static final String PARAM_SECURITY_CLIENTVERSION = "clientVersion";

    public static final String PARAM_SECURITY_UUID = "uuid";

    public static final String PARAM_SECURITY_TIME = "time";

    public static final String PARAM_SECURITY_USERID = "wpsuserid";


    /** 与微信有关的常量 */
    public static final String WX_UNIONID = "unionid";

    /** 与加密算法有关的常量 */
    public static final String ALGORITHM_MD5 = "MD5";

    /** 发送通知的redis消息队列key值 */
    public static final String SYSTEM_MESSAGE_QUEUE_NOTIFY = "wps_calendar::sys_notify::queue";

    /** 发送提醒的redis消息队列key值 */
    public static final String SYSTEM_MESSAGE_QUEUE_REMIND = "wps_calendar::sys_remind::queue";

    public static final String VIEW_PC = "PC";
    public static final String VIEW_MINIPROGRAM = "miniProgram";

}
