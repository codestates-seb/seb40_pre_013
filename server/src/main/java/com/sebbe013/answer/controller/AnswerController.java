package com.sebbe013.answer.controller;

import com.sebbe013.answer.dto.AnswerDto;
import com.sebbe013.answer.entity.Answer;
import com.sebbe013.answer.mapper.AnswerMapper;
import com.sebbe013.answer.service.AnswerService;
import com.sebbe013.exception.bussiness.BusinessLogicException;
import com.sebbe013.exception.bussiness.ExceptionCode;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Objects;

@RestController
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController {
    private final AnswerService answerService;

    private final MemberService memberService;
    private final AnswerMapper mapper;

    private final HttpServletRequest httpServletRequest;

    public AnswerController(AnswerService answerService, MemberService memberService, AnswerMapper mapper, HttpServletRequest httpServletRequest) {
        this.answerService = answerService;
        this.memberService = memberService;
        this.mapper = mapper;
        this.httpServletRequest = httpServletRequest;
    }
    // 답변 등록
    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody) {
        Answer answer = mapper.answerPostToAnswer(requestBody, memberService, httpServletRequest);
        Answer createdAnswer = answerService.createAnswer(answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(createdAnswer, memberService);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    // 답변 수정
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(
            @PathVariable("answer-id") @Positive long answerId,
            @Valid @RequestBody AnswerDto.Patch requestBody) {
        // 요청으로 들어온 답변 조회
        requestBody.setAnswerId(answerId);
        Answer findAnswer = answerService.findVerifiedAnswer(answerId);
        // 해당 회원이 작성한 답변이 아닐 경우 예외처리
        if (!Objects.equals(findAnswer.getMember().getMemberId(), memberService.findMemberId(httpServletRequest)))
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_ALLOWED);
        // 답변 수정
        Answer answer =
                answerService.updateAnswer((mapper.answerPatchToAnswer(requestBody, memberService, httpServletRequest, findAnswer.getQuestion().getQuestionId())));

        Member findMember = memberService.findVerifiedMember(answer.getMember().getMemberId());

        AnswerDto.Response response = mapper.answerToAnswerResponse(answer, memberService);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // 답변 삭제
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        // 해당 회원이 작성한 답변이 아닐 경우 예외처리
        if (answerService.findVerifiedAnswer(answerId).getMember().getMemberId()
                != memberService.findMemberId(httpServletRequest)) throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_ALLOWED);
        // 답변 삭제
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
