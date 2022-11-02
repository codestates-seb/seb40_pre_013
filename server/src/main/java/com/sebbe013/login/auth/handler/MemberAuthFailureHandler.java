package com.sebbe013.login.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.exception.dto.ErrorResponse;
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

/*
로그인 실패시 마지막에 오는 클래스
 */
@Component
@Slf4j
public class MemberAuthFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response, AuthenticationException exception ) throws IOException, ServletException{
        log.error(exception.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponse exceptions = ErrorResponse.builder() //errorresponse객체에 상태코드와 메시지 주입
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("아이디와 비밀번호를 확인해주세요!")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String errorResponse = objectMapper.writeValueAsString(exceptions); //json형태로 변경

        response.getWriter().write(errorResponse); //바디에 출력
    }
}
