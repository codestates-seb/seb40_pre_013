package com.sebbe013.question.controller;

import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.dto.QuestionDto;
import com.sebbe013.question.entity.Question;
import com.sebbe013.question.mapper.QuestionMapper;
import com.sebbe013.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    private final MemberService memberService;

    public QuestionController (QuestionService questionService,
                               QuestionMapper mapper,
                               MemberService memberService) {
        this.questionService = questionService;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    // 질문 등록하기
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.Post questionPostDto,
                                       HttpServletRequest request) {

        long memberId = memberService.findMemberId(request);        // 요청한 클라이언트의 member id
        questionPostDto.setMemberId(memberId);                      // 질문 PostDto에 작성자 추가

        // QuestionPostDto를 Question클래스로 변환하여 Question서비스에서 질문 등록
        Question question =
                questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));

        // RespseonEntity로 리턴
        return new ResponseEntity<>(
                mapper.questionToQuestionResponseDto(question),
                HttpStatus.CREATED);
    }

    /**
     * 질문 수정하기
     * @param questionId : 질문 id
     * @param questionPatchDto : 질문 Patch Dto
     * @param request   : 클라이언트로부터 request
     * @return
     */
    // 질문 수정하기
    @PatchMapping("/{question-id}")
    public ResponseEntity patchMember(
            @PathVariable("question-id") long questionId,
            @RequestBody QuestionDto.Patch questionPatchDto,
            HttpServletRequest request) {

        // questionPatchDto에 질문ID 설정
        questionPatchDto.setQuestionId(questionId);

        // questionPatchDto에 memberid 설정
        long memberId = memberService.findMemberId(request);        // 요청한 클라이언트의 member id
        questionPatchDto.setMemberId(memberId);                      // question Patch Dto에 수정자 추가

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

    /**
     * 질문 하나 삭제하기
     * @param questionId - 삭제할 질문 Id
     * @param request - 클라이언트로부터 http 요청
     * @return responseEntity - 내용없음
     */
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(
            @PathVariable("question-id") long questionId,
            HttpServletRequest request) {

        // 요청한 클라이언트의 memberid
        long memberId = memberService.findMemberId(request);

        // questionService에서 해당 질문 삭제하기
        questionService.deleteQuestion(questionId, memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
