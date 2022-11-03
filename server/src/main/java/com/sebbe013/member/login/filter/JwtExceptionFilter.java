package com.sebbe013.member.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.exception.response.ErrorResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
jwt 예외 처리 필터
 */
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException{
        try{
            filterChain.doFilter(request, response);
        }catch(JwtException e){
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED );
        }
    }
    public static void sendErrorResponse( HttpServletResponse response, HttpStatus status ) throws IOException{

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();

        ErrorResponse exceptions = ErrorResponse.builder() //errorresponse객체에 상태코드와 메시지 주입
                .status(404)
                .message(status.getReasonPhrase())
                .build();
        String errorResponse = objectMapper.writeValueAsString(exceptions); //json형태로 변경
        response.getWriter().write(errorResponse); //바디에 출력
    }

}
