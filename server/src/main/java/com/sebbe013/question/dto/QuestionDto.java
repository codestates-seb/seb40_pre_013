package com.sebbe013.question.dto;

import com.sebbe013.validator.NotSpace;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class QuestionDto {

    @Getter
    @Builder
    public static class Post {
        @NotBlank(message = "질문 제목은 공백이 아니어야 합니다")
        private String questionTitle;           // 질문 제목

        @NotBlank(message = "질문 내용은 공백이 아니어야 합니다")
        private String questionContent;         // 질문 내용

        private long memberId;                  // 질문 작성자 id

        /**
         * 질문 작성자 ID 설정
         * @param memberId: 질문 작성자 id
         */
        public void setMemberId(long memberId) {this.memberId = memberId; }

    }

    @Getter
    @Builder
    public static class Patch {
        private long questionId;            // 질문 id

        // @NotSpace: 제목 또는 내용 한가지만 수정할 수는 있지만 내용이 공백인 것은 허용되지 않음
        @NotSpace(message = "질문 제목에 공백을 입력할 수 없습니다")
        private String questionTitle;       // 질문 제목
        @NotSpace(message = "질문 내용에 공백을 입력할 수 없습니다")
        private String questionContent;     // 질문 내용

        /*
        질문ID 설정
        @param questionId: 질문ID
         */
        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }

        private long memberId;                  // 질문 수정자 id

        /**
         * 질문 수정자 ID 설정
         * @param memberId: 질문 수정자 id
         */
        public void setMemberId(long memberId) {this.memberId = memberId; }
    }

    @Getter
    @Builder
    public static class Response {
        private long questionId;                // 질문 아이디
        private String questionTitle;           // 질문 제목
        private String questionContent;         // 질문 내용
        private Long memberId;                  // 질문 작성자 아이디
        private String questionWriter;          // 질문 작성자
        private LocalDateTime createdAt;        // 작성 날짜
        private LocalDateTime modifiedAt;       // 수정 날짜
    }
}
