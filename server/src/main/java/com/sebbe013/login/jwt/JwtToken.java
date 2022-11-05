package com.sebbe013.login.jwt;

import com.sebbe013.member.entity.Member;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 jwt토큰 생성 클래스
 */
@Component
@RequiredArgsConstructor
public class JwtToken {

    private final Expiration expir;
    private final SecretKey secretKey;

    //acess토큰 생성 메서드
    public String createAcessToken( Map<String, Object> claims, String subject, Date expiration, Key key ){

        return Jwts.builder().setClaims(claims)//인증된 사용자와 관련된 정
                .setSubject(subject)//토큰이름
                .setIssuedAt(Calendar.getInstance().getTime())//생성일
                .setExpiration(expiration)//만료일
                .signWith(key)//서명을 위한 키
                .compact();
    }

    //refresh 토큰 생성
    public String createRefreshToken( String subject, Date expiration, Key key ){

        return Jwts.builder().setSubject(subject)//토큰이름
                .setIssuedAt(Calendar.getInstance().getTime())//생성일
                .setExpiration(expiration)//만료일
                .signWith(key)//서명을 위한 키
                .compact();
    }

    // jws만 추출
    public String extractJws( HttpServletRequest request ){
        String jws = request.getHeader("Authorization").replace("Bearer ", "");//authorization의 밸류 값에서, brear 제거
        return jws;
    }

    //유저정보를 토큰에 담는 메서드
    public String delegateAccessToken( Member member ){
        Map<String, Object> claims = new HashMap<>(); //필요한 정보를 담는다.
        claims.put("username", member.getEmail());
        claims.put("memberId", member.getMemberId());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail(); //토큰 이름??
        Date expiration = expir.getExpiration(expir.getAccessTokenExpirationMinutes());//만료시간
        Key key = secretKey.getSecretKey(secretKey.getBaseKey());  //시크릿키 생성

        return createAcessToken(claims, subject, expiration, key); // 담은 정보로 토큰 생성
    }

    //리프레시 토큰 생성
    public String delegateRefreshToken( Member member ){
        String subject = member.getEmail();
        Date expiration = expir.getExpiration(expir.getRefreshTokenExpirationMinutes());
        Key key = secretKey.getSecretKey(secretKey.getBaseKey());

        return createRefreshToken(subject, expiration, key);
    }
}

