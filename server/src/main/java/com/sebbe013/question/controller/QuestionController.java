package com.sebbe013.question.controller;

import com.sebbe013.answer.dto.AnswerDto;
import com.sebbe013.answer.dto.QuestionAnswersResponseDto;
import com.sebbe013.answer.entity.Answer;
import com.sebbe013.answer.mapper.AnswerMapper;
import com.sebbe013.answer.service.AnswerService;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.dto.QuestionDto;
import com.sebbe013.question.entity.Question;
import com.sebbe013.question.mapper.QuestionMapper;
import com.sebbe013.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@Validated
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final MemberService memberService;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;


    public QuestionController( QuestionService questionService,
                               QuestionMapper mapper,
                               AnswerService answerService,
                               AnswerMapper answerMapper,
                               MemberService memberService) {
        this.questionService = questionService;
        this.mapper = mapper;
        this.answerService = answerService;
        this.answerMapper = answerMapper;
         this.memberService = memberService;
    }

    // 질문 등록하기
    @PostMapping
    public ResponseEntity postQuestion(@RequestBody @Valid QuestionDto.Post questionPostDto,
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
            @PathVariable("question-id") @Positive long questionId,
            @RequestBody @Valid QuestionDto.Patch questionPatchDto,
            HttpServletRequest request) {

        // questionPatchDto에 질문ID 설정
        questionPatchDto.setQuestionId(questionId);

        // questionPatchDto에 memberid 설정
        long memberId = memberService.findMemberId(request);        // 요청한 클라이언트의 member id
        questionPatchDto.setMemberId(memberId);                      // question Patch Dto에 수정자 추가

        // questionService에서 질문 업데이트
        Question question =
                questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));
        
        // 질문에 대한 답변 리스트 
        List<Answer> answers = answerService.findAnswers(questionId);
        // 답변 리스트 ResponseDto 리스트로 변환
        List<AnswerDto.Response> answersResponse = answerMapper.answersToAnswerResponses(answers);
        
        // 질문 ResponseDTo, 답변 responseDto리스트, HttpStatus.ok와 함께 반환
        return new ResponseEntity<>(
                new QuestionAnswersResponseDto<>(
                        mapper.questionToQuestionResponseDto(question), answersResponse),
                        HttpStatus.OK);
    }

    // 전체 질문 목록 조회하기
   @GetMapping
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

   // 질문 하나 조회하기 (그 질문에 해당하는 답변까지 같이 보이도록)

    /**
     * 질문 하나 조회하기
     * 질문에 해당하는 내용, 답변 전부 출력
     * @param questionId - 질문 id
     * @return responseEntity - 질문 response dto,답변 response Dto리스트, HTTP Status OK
     */
   @GetMapping("/{question-id}")
   public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {
       Question question = questionService.findQuestion(questionId);
       List<Answer> answers = answerService.findAnswers(questionId);
       List<AnswerDto.Response> answersResponse = answerMapper.answersToAnswerResponses(answers);
       return new ResponseEntity<>(new QuestionAnswersResponseDto<>(mapper.questionToQuestionResponseDto(question), answersResponse),
               HttpStatus.OK);
   }
  
   /**
     * 질문 하나 삭제하기
     * @param questionId - 삭제할 질문 Id
     * @param request - 클라이언트로부터 http 요청
     * @return responseEntity - 내용없음
     */
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(
            @PathVariable("question-id") @Positive long questionId,
            HttpServletRequest request) {

        // 요청한 클라이언트의 memberid
        long memberId = memberService.findMemberId(request);

        // questionService에서 해당 질문 삭제하기
        questionService.deleteQuestion(questionId, memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
