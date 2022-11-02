package com.sebbe013.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorResponder {

    public static void sendErrorResponse( HttpServletResponse response, HttpStatus status ) throws IOException{

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();

        ErrorResponse exceptions = ErrorResponse.builder() //errorresponse객체에 상태코드와 메시지 주입
                .code(status.value())
                .message(status.name())
                .build();
        String errorResponse = objectMapper.writeValueAsString(exceptions); //json형태로 변경
        response.getWriter().write(errorResponse); //바디에 출력
    }
}
