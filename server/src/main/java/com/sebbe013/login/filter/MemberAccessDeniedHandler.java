package com.sebbe013.login.filter;

import com.sebbe013.login.exception.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
/*
인증에는 성공했지만 해당 리소스에 대한 권한이 없는 경우 호출되는 핸들러 클래스
 */
public class MemberAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle( HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException ) throws IOException, ServletException{
        log.warn("권한 없는 사용자");
        ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN);
    }
}
