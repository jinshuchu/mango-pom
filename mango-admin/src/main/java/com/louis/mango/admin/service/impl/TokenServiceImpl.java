package com.louis.mango.admin.service.impl;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.louis.mango.admin.service.ITokenService;
import com.louis.mango.admin.vo.Payload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-08-01
 */

@Service
public class TokenServiceImpl implements ITokenService {

    private String TOKEN_SECRET;
    private JWTSigner mSigner;
    private JWTVerifier mVerifier;
    private Algorithm mAlgorithm;

    @Value("${wx.token.secret}")
    public void setTokenSecret(String tokenSecret) {
        TOKEN_SECRET = tokenSecret;
        mSigner = new JWTSigner(TOKEN_SECRET);
        mVerifier = new JWTVerifier(TOKEN_SECRET);
        mAlgorithm = Algorithm.HS256;
    }

    public String generate(String userId, String userVerify, int expires) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(Payload.USER_ID, userId);
        claims.put(Payload.USER_VERIFY, userVerify);
        claims.put(Payload.TIME, System.currentTimeMillis());
        claims.put(Payload.SECRET, Payload.TOKEN_SECRET);

        JWTSigner.Options options = new JWTSigner.Options()
                .setAlgorithm(mAlgorithm)
                .setExpirySeconds(expires)
                .setNotValidBeforeLeeway(5)
                .setIssuedAt(true)
                .setJwtId(true);

        String token = mSigner.sign(claims, options);
        return token;
    }

    public String generate(String userId, String userVerify) {
        return generate(userId, userVerify, Payload.EXP_SECOND);
    }

    public Payload decode(String token) {
        Payload payload = Payload.ILLEGAL_PAYLOAD;
        try {
            Map<String, Object> decoded = mVerifier.verify(token);
            Payload.TokenPayload result = Payload.build(
                    (String) decoded.get(Payload.USER_ID),
                    (String) decoded.get(Payload.USER_VERIFY),
                    (Long) decoded.get(Payload.TIME),
                    (String) decoded.get(Payload.SECRET));
            payload = result;
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeyException e) {
        } catch (IOException e) {
        } catch (SignatureException e) {
            // signature verification failed
        } catch (IllegalStateException e) {
            // token's structure is invalid
        } catch (JWTVerifyException e) {
            // jwt expired
            // jwt issuer invalid
            // jwt audience invalid
        }
        return payload;
    }

    public boolean isValid(Payload payload) {
        return payload != Payload.ILLEGAL_PAYLOAD;
    }
}