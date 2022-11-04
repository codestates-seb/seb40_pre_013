package com.sebbe013.exception.bussiness;

import lombok.Getter;

public enum ExceptionCode {
    ANSWER_NOT_FOUND(404, "답변을 찾을 수 없어요"),
    MEMBER_NOT_FOUND(404, "Member not found"), // 에러 추가, 나중에 생각
    QUESTION_NOT_FOUND(404, "질문을 찾을 수 없습니다^^"),  // 질문이 존재하지 않을 때
    QUESTION_NOT_ALLOWED(404, "해당 질문의 작성자가 아닙니다."),     // 질문 수정, 삭제 불가
    EXIST_EMAIL(409,"이미 가입한 e-mail입니다."),
    EXIST_DISPALY_NAME(409,"이미 존재하는 닉네임입니다."),

    ANSWER_NOT_ALLOWED(404, "해당 답변의 작성자가 아닙니다."); // 답변 수정, 삭제 불가
    @Getter
    private int code;
    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
