package com.sebbe013.login.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
/*
토큰 만료 관련 클래스
 */
@Component
public class Expiration {
    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes; //기본 30분 설정

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;//리프레시 토큰 420분 설정

    //만료시간 설정 메서드
    public Date getExpiration( int tokenExpirationMinutes ){
        Calendar now = Calendar.getInstance(); //현재시간 추출
        now.add(Calendar.MINUTE, tokenExpirationMinutes);//현재시간에 기본 시간 더함
        Date expiration = now.getTime();//시간추출

        return expiration;
    }

}
