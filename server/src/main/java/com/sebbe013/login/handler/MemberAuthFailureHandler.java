package com.sebbe013.login.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sebbe013.login.handler.MemberAuthenticationEntryPoint.errorToJson;

/*
로그인 실패시 마지막에 오는 클래스
 */
@Component
@Slf4j
public class MemberAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response, AuthenticationException exception ) throws IOException, ServletException{
        log.error("로그인 실패");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("아이디와비밀번호를 확인해주세요");
        errorToJson(response, HttpStatus.UNAUTHORIZED);
    }
}
