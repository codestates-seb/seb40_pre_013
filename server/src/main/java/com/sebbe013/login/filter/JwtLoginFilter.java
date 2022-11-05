package com.sebbe013.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.login.jwt.Expiration;
import com.sebbe013.login.jwt.JwtToken;
import com.sebbe013.login.jwt.SecretKey;
import com.sebbe013.member.dto.LoginDto;
import com.sebbe013.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
/*
로그인시 수행되는 필터
 */

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    @Override
    @SneakyThrows//예외처리
    //로그인 시도 메서드
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response ) throws AuthenticationException{
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);//로그인 dto 필드값 json값으로 변

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());//아이디와 비밀전호로 인증 진행
                                                                                                       // loadUserBy~함수가 실행, 로그인 시도, memberdetailservice가 실행됨
        return authenticationManager.authenticate(authenticationToken); //로그인 진행 완료
    }

    //로그인 성공시 수행되는 메서드
    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult )
            throws IOException, ServletException{
        Member member = (Member) authResult.getPrincipal(); //컨텍스트에 담긴 유저정보 추출

        String accessToken = jwtToken.delegateAccessToken(member); //유저정보를 이용해 토큰생성
        String refreshToken = jwtToken.delegateRefreshToken(member);//리프레시 토큰 생성

        response.setHeader("Authorization", "Bearer " + accessToken); // 응답 헤더에 토큰을 담는다.
        response.setHeader("Refresh", refreshToken); //응답 헤더에 리프레시 토큰을 담는다.

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);// MemberAuthsuccessfulhanler 호출, 로그인 성공

    }

}
