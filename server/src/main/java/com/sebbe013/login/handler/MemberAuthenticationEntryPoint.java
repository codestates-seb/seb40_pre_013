package com.sebbe013.login.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.exception.response.ErrorResponse;
import com.sebbe013.login.filter.JwtVerificationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response, AuthenticationException authException ) throws IOException, ServletException{
        log.info("엔트리포인트 진입");
        errorToJson(response, HttpStatus.UNAUTHORIZED);
    }
    //에러내용을 json형태로 보내줌
    public static void errorToJson( HttpServletResponse response, HttpStatus status ) throws IOException{

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse exceptions = ErrorResponse.builder() //errorresponse객체에 상태코드와 메시지 주입
                .status(status.value()).message(status.getReasonPhrase()).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String errorResponse = objectMapper.writeValueAsString(exceptions); //json형태로 변경

        response.getWriter().write(errorResponse); //바디에 출력
    }
}
