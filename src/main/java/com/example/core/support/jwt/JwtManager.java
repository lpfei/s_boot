package com.example.core.support.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description: java-jwt 场景:无状态,有时效的加密token,不支持token主动时效操作
 * Created by lpfei on 2019/8/9
 */
@Component
@Slf4j
public class JwtManager {

    private static final String secret = "111111";

    private String getToken(String param, String secret, Date expireAt) {
        return JWT.create().withAudience(param).withExpiresAt(expireAt).sign(Algorithm.HMAC256(secret));
    }

    /**
     * 生成有效时间token
     *
     * @param param
     * @param expireAt
     * @return
     */
    public String getToken(String param, Date expireAt) {
        return getToken(param, secret, expireAt);
    }

    /**
     * 验证token
     *
     * @param token
     */
    public void verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        //expire
        //TokenExpiredException: The Token has expired on Fri Aug 09 11:24:27 CST 2019
        jwtVerifier.verify(token);
    }

    /**
     * 获取token内信息
     *
     * @param token
     * @return
     */
    public String decodeToken(String token) {
        //null
        //The token was expected to have 3 parts, but got 1
        return JWT.decode(token).getAudience().get(0);
    }

    public static void main(String[] args) {
        JwtManager manager = new JwtManager();
        String token = manager.getToken("1", new JDateTime().addSecond(5).convertToDate());
        System.out.println(token);
        System.out.println(manager.decodeToken(token));
        manager.verifyToken(token);
    }
}
