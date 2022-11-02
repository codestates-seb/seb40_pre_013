package com.sebbe013.question.controller;

import com.sebbe013.question.dto.QuestionDto;
import com.sebbe013.question.entity.Question;
import com.sebbe013.question.mapper.QuestionMapper;
import com.sebbe013.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;


    public QuestionController (QuestionService questionService,
                               QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    // 질문 등록하기
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.Post questionPostDto) {
        // QuestionPostDto를 Question클래스로 변환하여 Question서비스에서 질문 등록
        Question question =
                questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));

        // RespseonEntity로 리턴
        return new ResponseEntity<>(
                mapper.questionToQuestionResponseDto(question),
                HttpStatus.CREATED);
    }

    /*
    질문 수정하기
    @param questionId : 질문 id
    @param questionPatchDto: 질문 patch DTO
     */
    // 질문 수정하기
    @PatchMapping("/{question-id}")
    public ResponseEntity patchMember(
            @PathVariable("question-id") long questionId,
            @RequestBody QuestionDto.Patch questionPatchDto) {

        // questionPatchDto에 질문ID 설정
        questionPatchDto.setQuestionId(questionId);

        // questionService에서 질문 업데이트
        Question question =
                questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));

        // TODO: 답변 DTO와 함께리턴 필요함
        return new ResponseEntity<> (
                mapper.questionToQuestionResponseDto(question),
                HttpStatus.OK);
    }

}
