package com.sebbe013.login.auth.handler;

import com.google.gson.Gson;
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

@Component
@Slf4j
public class MemberAuthFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response, AuthenticationException exception ) throws IOException, ServletException{
        log.error(exception.getMessage());
        Gson gson = new Gson();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        log.error(response.getContentType());
        response.getWriter().write(gson.toJson(response));
    }
}
