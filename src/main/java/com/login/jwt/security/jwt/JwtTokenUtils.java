package com.login.jwt.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.login.jwt.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 3일 (단위: seconds)
    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String JWT_SECRET = "jwt_secret_!@#$%";

    public static String generateJwtToken(UserDetailsImpl userDetails) {
        // access token
        String token = null;
        //refresh token
        String refreshToken = null;
        try {
            token = JWT.create()
                    // 토큰 발급자
                    .withIssuer("jwt1234")
                    // 토큰에 포함된 유저이름
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간) // 10초 세팅
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + 10000))
                    // 암호화한 secretKey
                    .sign(generateAlgorithm());
            log.info("info -> 현재시간 ={}",new Date(System.currentTimeMillis()));
            log.info("토큰 유효시간 ={}",new Date(System.currentTimeMillis() + 10000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

    //    public String generateRefreshToken(){
//        String refreshToken = JWT.create(null,"refreshToken",JWT_TOKEN_VALID_SEC*1000);
//        return "";
//    }

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }
}

