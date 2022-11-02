package com.sebbe013.question.controller;

import com.sebbe013.question.dto.QuestionDto;
import com.sebbe013.question.entity.Question;
import com.sebbe013.question.mapper.QuestionMapper;
import com.sebbe013.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    // 전체 질문 목록 조회하기
   @GetMapping("/")
    public ResponseEntity getQuestions() {
        // questionService에서 질문들 모두 가져와서 List로 생성
        List<Question> questions = questionService.findQuestions();

        // Question 리스트의 질문들을 ResponseDto로 변환후 List로 변환
        List<QuestionDto.Response> response =
                questions.stream()
                        .map(question -> mapper.questionToQuestionResponseDto(question))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   // 질문 하나 삭제하기
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(
            @PathVariable("question-id") long questionId) {
        // TODO: 요청자의 ID와 질문작성자의 ID가 일치하는지 확인 필요

        // questionService에서 해당 질문 삭제하기
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
