package com.sebbe013.answer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AnswerDto {

    @Getter
    public static class Post {

        private long memberId;

        @NotBlank(message = "답변 내용은 공백이 아니어야 합니다.")
        private String answerContent;
    }

    @Getter
    public static class Patch {
        private long answerId;
        private long memberId; // 수정

        @NotBlank(message = "답변 수정 내용은 공백이 아니어야 합니다.")
        private String answerContent;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @Getter
    @Setter   // AccessLevel 설정?
    public static class Response {
        private long answerId;
        private String writer;
        private String answerContent;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;
    }
}
