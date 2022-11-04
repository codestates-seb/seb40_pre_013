package com.sebbe013.login.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response, AuthenticationException authException ) throws IOException, ServletException{
        log.info("엔트리포인트진입");


//            ObjectMapper objectMapper = new ObjectMapper();
//        ErrorResponse exceptions = ErrorResponse.builder() //errorresponse객체에 상태코드와 메시지 주입
//                .status(404).message("만료된 토큰입니다.").build();
//        String errorResponse = objectMapper.writeValueAsString(exceptions); //json형태로 변경
//        response.getWriter().write(errorResponse); //바디에 출력
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED);

    }

    public static void sendErrorResponse( HttpServletResponse response, HttpStatus status) throws IOException{

        response.setContentType(APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse exceptions = ErrorResponse.builder() //errorresponse객체에 상태코드와 메시지 주입
                .status(status.value()).message(status.getReasonPhrase()).build();
        log.info("ErrorResponse = {}", exceptions.getMessage());
        String errorResponse = objectMapper.writeValueAsString(exceptions); //json형태로 변경
        response.getWriter().write(errorResponse); //바디에 출력
    }
}
