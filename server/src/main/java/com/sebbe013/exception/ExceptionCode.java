package com.sebbe013.exception;

import lombok.Getter;

public enum ExceptionCode {
    ANSWER_NOT_FOUND(404, "Answer not found"),
    MEMBER_NOT_FOUND(404, "Member not found"), // 에러 추가

    MEMBER_INCONSISTENCY(404, "Member Inconsistency");
    @Getter
    private int code;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}