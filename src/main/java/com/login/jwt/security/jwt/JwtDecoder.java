package com.login.jwt.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.login.jwt.security.jwt.JwtTokenUtils.*;


@Component
public class JwtDecoder {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String decodeUsername(String token) {
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new IllegalArgumentException("유효한 토큰이 아닙니다."));

        Date expiredDate = decodedJWT
                .getClaim(CLAIM_EXPIRED_DATE)
                .asDate();

        log.info("토큰 유효시간 ={}",expiredDate);


        Date now = new Date();
        if (expiredDate.before(now)) {
            throw new IllegalArgumentException("유료한 시간이 지난 토큰입니다.");
        }

        String username = decodedJWT
                .getClaim(CLAIM_USER_NAME)
                .asString();

        return username;
    }

    private Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT jwt = null;

        try {
            // 비밀키를 HMAC256 알고리즘으로 암호화
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            // 토큰 해독 객체 생성
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();
            // 토큰 검증
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }
}
