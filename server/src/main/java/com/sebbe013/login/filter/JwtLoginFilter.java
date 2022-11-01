package com.sebbe013.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtToken jwtToken;
    private final SecretKey secretKey;
    private final Expiration expir;

    @Override
    @SneakyThrows //예외처리해줌
    public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response ) throws AuthenticationException{
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        // loadUserBy~함수가 실행
        // 그러면 PrincipaDetails를 세션에 담고, Jwt토큰을 만들어서 응답해주면됨.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult )
            throws IOException, ServletException{
        Member member = (Member) authResult.getPrincipal();

        String accessToken = delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);

        response.setHeader("Authoriztion", "Bearer " + accessToken); // 응답 헤더에 담을 내용
        response.setHeader("Refresh", refreshToken);

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);// MemberAuthsuccessfulhanler호출

    }

    private String delegateAccessToken( Member member ){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();
        Date expiration = expir.getExpiration(expir.getAccessTokenExpirationMinutes());
        String encodedSecretKey = secretKey.encodeSecretKey(secretKey.getBaseKey());
        Key key = secretKey.getKeyFromEncodedKey(encodedSecretKey);

        return jwtToken.createAcessToken(claims, subject, expiration, key);
    }

    private String delegateRefreshToken( Member member ){
        String subject = member.getEmail();
        Date expiration = expir.getExpiration(expir.getRefreshTokenExpirationMinutes());
        String encodedSecretKey = secretKey.encodeSecretKey(secretKey.getBaseKey());
        Key key = secretKey.getKeyFromEncodedKey(encodedSecretKey);

        return jwtToken.createRefreshToken(subject, expiration, key);
    }



}
