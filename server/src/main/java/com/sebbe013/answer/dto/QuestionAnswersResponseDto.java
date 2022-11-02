package com.sebbe013.answer.dto;

import com.sebbe013.question.dto.QuestionDto;
import lombok.Getter;

import java.util.List;

@Getter
public class QuestionAnswersResponseDto<T> {

    private QuestionDto.Response questionResponse;
    private List<T> answerData;

    public QuestionAnswersResponseDto(QuestionDto.Response questionResponse, List<T> answerData) {
        this.questionResponse = questionResponse;
        this.answerData = answerData;
    }
}
