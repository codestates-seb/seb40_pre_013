package com.sebbe013.answer.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AnswerDto {

    @Getter
    @Builder
    public static class Post {

        private long questionId;

        @NotBlank(message = "답변 내용은 공백이 아니어야 합니다.")
        private String answerContent;
    }

    @Getter
    public static class Patch {
        private long answerId;

        @NotBlank(message = "답변 수정 내용은 공백이 아니어야 합니다.")
        private String answerContent;

        public void setAnswerId(long answerId) {
            this.answerId = answerId;
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private long answerId;
        private String writer;
        private String answerContent;
        private LocalDateTime modifiedAt;
    }
}
