package com.sebbe013.member.login.config;

import com.sebbe013.member.login.auth.handler.MemberAuthFailureHandler;
import com.sebbe013.member.login.auth.handler.MemberAuthSuccessHandler;
import com.sebbe013.member.login.filter.JwtExceptionFilter;
import com.sebbe013.member.login.filter.JwtLoginFilter;
import com.sebbe013.member.login.filter.JwtVerificationFilter;
import com.sebbe013.member.login.jwt.Expiration;
import com.sebbe013.member.login.jwt.JwtToken;
import com.sebbe013.member.login.jwt.SecretKey;
import com.sebbe013.redis.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@RequiredArgsConstructor
public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
    private final JwtToken jwtToken;
    private final SecretKey secretKey;
    private final Expiration expiration;
    private final RedisConfig redisConfig;

    @Override
    public void configure( HttpSecurity builder ) throws Exception{
        // authenticationManager 객체를 얻어 로그인 할 때 사용
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class); // authenticationManager 객체를 얻을 수 있다.

        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(authenticationManager, jwtToken, secretKey, expiration);//필터 실행
        jwtLoginFilter.setFilterProcessesUrl("/members/login"); //로그인 디폴트 url

        jwtLoginFilter.setAuthenticationFailureHandler(new MemberAuthFailureHandler());//로그인 실패시 핸들러 설정
        jwtLoginFilter.setAuthenticationSuccessHandler(new MemberAuthSuccessHandler());//로그인 성공시 핸들러 설정

        JwtExceptionFilter jwtExceptionFilter = new JwtExceptionFilter();
        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(secretKey, redisConfig, jwtToken); //jwt인증 필터 설정

        builder.addFilter(jwtLoginFilter) //로그인 필터 추가
                .addFilterAfter(jwtVerificationFilter, JwtLoginFilter.class)//로그인 필터가 실행된 바로 다음 jwt인증 필터 실행
                .addFilterBefore(jwtExceptionFilter, JwtVerificationFilter.class);
    }
}