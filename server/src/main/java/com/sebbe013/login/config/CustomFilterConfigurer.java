package com.sebbe013.login.config;

import com.sebbe013.login.auth.handler.MemberAuthFailureHandler;
import com.sebbe013.login.auth.handler.MemberAuthSuccessHandler;
import com.sebbe013.login.filter.Expiration;
import com.sebbe013.login.filter.JwtLoginFilter;
import com.sebbe013.login.filter.JwtVerificationFilter;
import com.sebbe013.login.jwt.JwtToken;
import com.sebbe013.login.jwt.SecretKey;
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

    @Override
    public void configure( HttpSecurity builder ) throws Exception{
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class); // authenticationManager 객체를 얻을 수 있다.

        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(authenticationManager, jwtToken,  secretKey, expiration);
        jwtLoginFilter.setFilterProcessesUrl("/login"); //로그인 디폴트 url변경
        jwtLoginFilter.setAuthenticationFailureHandler(new MemberAuthFailureHandler());//로그인실패
        jwtLoginFilter.setAuthenticationSuccessHandler(new MemberAuthSuccessHandler());//로그인 성공

        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(secretKey);

        builder.addFilter(jwtLoginFilter)
                .addFilterAfter(jwtVerificationFilter, JwtLoginFilter.class);

    }
}
