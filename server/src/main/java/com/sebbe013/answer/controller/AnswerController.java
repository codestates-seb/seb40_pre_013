package com.sebbe013.answer.controller;

import com.sebbe013.answer.dto.AnswerDto;
import com.sebbe013.answer.entity.Answer;
import com.sebbe013.answer.mapper.AnswerMapper;
import com.sebbe013.answer.service.AnswerService;
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

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody) {
        Answer answer = mapper.answerPostToAnswer(requestBody, memberService, httpServletRequest);
        Answer createdAnswer = answerService.createAnswer(answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(createdAnswer, memberService);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(
            @PathVariable("answer-id") @Positive long answerId,
            @Valid @RequestBody AnswerDto.Patch requestBody) {
        requestBody.setAnswerId(answerId);
        Answer findAnswer = answerService.findVerifiedAnswer(answerId);

        Answer answer =
                answerService.updateAnswer((mapper.answerPatchToAnswer(requestBody, memberService, httpServletRequest, findAnswer.getQuestion().getQuestionId())));


        Member findMember = memberService.findVerifiedMember(answer.getMember().getMemberId());

        AnswerDto.Response response = mapper.answerToAnswerResponse(answer, memberService);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
