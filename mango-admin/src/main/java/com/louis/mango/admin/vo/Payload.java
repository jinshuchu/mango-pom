package com.louis.mango.admin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  登录时存放于token中的实体类
 * Created by HuangWenhuan on 14-11-4.
 */
@Getter
@Setter
@ToString
public abstract class Payload {

    public static final String USER_ID = "userid";
    public static final String USER_VERIFY = "userverify";
    public static final String TIME = "time";
    public static final String SECRET = "tokensecret";

    public static final Long TOKEN_TIME = 1000 * 60 * 60 * 24 * 7 * 4L;
    public static final Integer EXP_SECOND = 60 * 60 * 24 * 7 * 4;
    public static final String TOKEN_SECRET = "token_secret";

    public static final String ISS = "iss";
    public static final String SUB = "sub";
    public static final String AUD = "aud";
    public static final String EXP = "exp";
    public static final String NBF = "nbf";
    public static final String IAT = "iat";
    public static final String JTI = "jti";

    public static final Payload ILLEGAL_PAYLOAD = new IllegalPayload();

    public static TokenPayload build(String userid, String userVerify, Long time, String secret) {
        TokenPayload payload = new TokenPayload();
        payload.setUserId(userid);
        payload.setUserVerify(userVerify);
        payload.setTime(time);
        payload.setSecret(secret);
        return payload;
    }

    public static TokenPayload build(long userId, String userVerify) {
        return build(String.valueOf(userId), userVerify, System.currentTimeMillis(), SECRET);
        //user.getUserId().toString(), user.getUserVerify()
    }


    @Getter
    @Setter
    @ToString
    public static class TokenPayload extends Payload {
        private String userId;
        private String userVerify;
        private Long time;
        private String secret;
    }

    private static class IllegalPayload extends Payload {

    }

}