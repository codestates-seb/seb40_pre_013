package com.sebbe013.member.service;

import com.sebbe013.login.filter.JwtVerificationFilter;
import com.sebbe013.login.jwt.JwtToken;
import com.sebbe013.login.jwt.SecretKey;
import com.sebbe013.redis.RedisConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
@RequiredArgsConstructor
public class Logout {
    private final JwtToken jwtToken;
    private final SecretKey secretKey;
    private final JwtVerificationFilter jwtVerificationFilter;
    private final RedisConfig redis;
    private final String REDIS_KEY_PREFIX = "logouttoken";



    //로그아웃 실행 메서드
    public void logout( HttpServletRequest request ){

        String jws = jwtToken.extractJws(request);
        Key key = secretKey.getSecretKey(secretKey.getBaseKey());
        Jws<Claims> claims = jwtVerificationFilter.getClaims(jws, key);
        Date expiration = claims.getBody().getExpiration();

        if(notLogoutedToken(request)){
            redis.redisTemplate().opsForValue().set(REDIS_KEY_PREFIX + jws, "tk", expiration.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            log.info("로그아웃 완료"); //로그아웃은 여기서 시키는겁니다
        }
    }

    //로그아웃된 유저의 토큰인지 확인하는 메서드
    private Boolean notLogoutedToken( HttpServletRequest request ){

        String jws = jwtToken.extractJws(request);
        Key key = secretKey.getSecretKey(secretKey.getBaseKey());
        Jws<Claims> claims = jwtVerificationFilter.getClaims(jws, key);
        boolean notExpire = claims.getBody().getExpiration().after(new Date());//현재시간보다 만료일이 더 나중이면 true

        if(redis.redisTemplate().opsForValue().get(REDIS_KEY_PREFIX + jws) != null){ //만약 레디스에 토큰이 있으면 로그아웃된 유저
            log.info("로그아웃 됐습니다.");
            return false;
        }
        return notExpire;
    }
}
