package com.sebbe013.exception;

import lombok.Getter;

public enum ExceptionCode {
    ANSWER_NOT_FOUND(404, "Answer not found"),
    MEMBER_NOT_FOUND(404, "Member not found"), // 에러 추가

    MEMBER_INCONSISTENCY(404, "Member Inconsistency"),

    QUESTION_NOT_FOUND(404, "Question not found"),  // 질문이 존재하지 않을 때
    QUESTION_NOT_ALLOWED(401, "Question not allowed");        // 질문 수정, 삭제 불가
    @Getter
    private int code;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
