package com.sebbe013.login.jwt;

import com.sebbe013.member.entity.Member;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JwtTokenTest {
    private String baseKey = "dfkemekemkm12312rtrtrtrtrtg3123123123";
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private SecretKey secretKey;

    @Test
    void acesse토큰_생성() throws Exception{
        //given
        String baseKey = "dfkemekemkm12312rtrtrtrtrtg3123123123";
        Key key = secretKey.getSecretKey(baseKey);
        Member member = Member.builder().memberId(1L).displayName("test").email("test@gmail.com").displayName("test").build();
        //when
        String acessToken = getAcessToken(key, member, 2, Calendar.MINUTE);
        //then
        assertThat(acessToken).isNotNull();
    }

    @Test
    @DisplayName("만료시간이 지나면 토큰을 사용할 수 없다.")
    void 토큰_만료() throws Exception{
        //given
        Key key = secretKey.getSecretKey(baseKey);
        Member member = Member.builder().memberId(1L).displayName("test").email("test@gmail.com").displayName("test").build();
        //when
        String acessToken = getAcessToken(key, member, 1, Calendar.SECOND);
        TimeUnit.MILLISECONDS.sleep(2000);

        //then
        assertThatExceptionOfType(ExpiredJwtException.class).isThrownBy(() ->{verifySignature(acessToken,key);});

    }

    @Test
    @DisplayName("jwt 서명이 인증되면 검증된 jwt이다.")
    void jwt_검증() throws Exception{
        //given
        String baseKey = "dfkemekemkm12312rtrtrtrtrtg3123123123";
        Key key = secretKey.getSecretKey(baseKey);
        Member member = Member.builder().memberId(1L).displayName("test").email("test@gmail.com").displayName("test").build();
        //when
        String acessToken = getAcessToken(key, member, 2, Calendar.MINUTE);
        //then
        assertThatCode(() -> {verifySignature(acessToken,key);}).doesNotThrowAnyException();
    }

    private String getAcessToken( Key key, Member member, int time, int timeUnit ){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("memberId", member.getMemberId());
        claims.put("displayName", member.getDisplayName());

        String subject = "test token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(timeUnit, time);
        Date expiration = calendar.getTime();

        String acessToken = jwtToken.createAcessToken(claims, subject, expiration, key);
        return acessToken;
    }

    private void verifySignature( String jws, Key key ){
        Jwts.parserBuilder().setSigningKey(key)     // (1)
                .build().parseClaimsJws(jws);   // (2)
    }

}