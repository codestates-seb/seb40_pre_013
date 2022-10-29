package com.sebbe013.stackoverflowclone.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private List<FieldError> fieldErrors;


    @Getter
    @AllArgsConstructor
    public static class FieldError{
        private String field;
        private Object rejectedValue;
        private String reason;
    }

}
