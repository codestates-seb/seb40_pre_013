package com.sebbe013.login.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class MemberAuthFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response, AuthenticationException exception ) throws IOException, ServletException{
        log.error(exception.getMessage());

        ObjectMapper objectMapper = new ObjectMapper();

      ErrorResponse exceptions = ErrorResponse.builder()
              .code(HttpStatus.UNAUTHORIZED.value())
              .message("아이디와 비밀번호를 확인해 주세요.")
              .build();
        String errorResponse = objectMapper.writeValueAsString(exceptions);
        response.getWriter().write(errorResponse);
    }
}
