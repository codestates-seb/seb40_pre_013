package com.sebbe013.login.config;

import com.sebbe013.login.filter.JwtLoginFilter;
import com.sebbe013.login.filter.JwtVerificationFilter;
import com.sebbe013.login.handler.MemberAuthFailureHandler;
import com.sebbe013.login.handler.MemberAuthSuccessHandler;
import com.sebbe013.login.jwt.JwtToken;
import com.sebbe013.login.jwt.SecretKey;
import com.sebbe013.redis.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
    private final JwtToken jwtToken;
    private final SecretKey secretKey;
    private final RedisConfig redisConfig;

    @Override
    public void configure( HttpSecurity builder ) throws Exception{
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(authenticationManager, jwtToken);//필터 실행
        jwtLoginFilter.setFilterProcessesUrl("/members/login"); //로그인 디폴트 url

        jwtLoginFilter.setAuthenticationFailureHandler(new MemberAuthFailureHandler());//로그인 실패시 핸들러 설정
        jwtLoginFilter.setAuthenticationSuccessHandler(new MemberAuthSuccessHandler());//로그인 성공시 핸들러 설정

        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(secretKey, redisConfig, jwtToken); //jwt인증 필터 설정

        builder.addFilter(jwtLoginFilter) //로그인 필터 추가
                .addFilterAfter(jwtVerificationFilter, JwtLoginFilter.class);//로그인 필터가 실행된 바로 다음 jwt인증 필터 실행
    }
}
