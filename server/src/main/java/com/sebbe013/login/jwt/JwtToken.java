package com.sebbe013.login.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/*
 jwt토큰 생성 클래스
 */
@Component
public class JwtToken {

    //acess토큰 생성 메서드
    public String createAcessToken( Map<String, Object> claims, String subject, Date expiration, Key key ){

        return Jwts.builder()
                .setClaims(claims)//인증된 사용자와 관련된 정
                .setSubject(subject)//토큰이름
                .setIssuedAt(Calendar.getInstance().getTime())//생성일
                .setExpiration(expiration)//만료일
                .signWith(key)//서명을 위한 키
                .compact();
    }

    //refresh 토큰 생성
    public String createRefreshToken( String subject, Date expiration, Key key ){

        return Jwts.builder()
                .setSubject(subject)//토큰이름
                .setIssuedAt(Calendar.getInstance().getTime())//생성일
                .setExpiration(expiration)//만료일
                .signWith(key)//서명을 위한 키
                .compact();
    }
    // jws만 추출
    public String extractJws( HttpServletRequest request){
        String jws = request.getHeader("Authorization").replace("Bearer ", "");//authorization의 밸류 값에서, brear 제거
         return jws;
    }

}

