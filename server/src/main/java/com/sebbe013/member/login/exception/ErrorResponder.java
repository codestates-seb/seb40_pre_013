package com.sebbe013.member.login.exception;

import com.google.gson.Gson;
import com.sebbe013.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
jwt토큰 인증 예외처리가 나면
 */
@Slf4j
public class ErrorResponder {

        public static void sendErrorResponse(HttpServletResponse response, HttpStatus status) throws IOException {
            Gson gson = new Gson();
            ErrorResponse errorResponse = ErrorResponse.of(status);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(status.value());
            response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
        }
}
