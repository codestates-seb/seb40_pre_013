package com.sebbe013.answer.controller;

import com.sebbe013.answer.dto.AnswerDto;
import com.sebbe013.answer.entity.Answer;
import com.sebbe013.answer.mapper.AnswerMapper;
import com.sebbe013.answer.service.AnswerService;
import com.sebbe013.exception.BusinessLogicException;
import com.sebbe013.exception.ExceptionCode;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    public AnswerController(AnswerService answerService, MemberService memberService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody) {
        Answer answer = mapper.answerPostToAnswer(requestBody);
        Answer createdAnswer = answerService.createAnswer(answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(createdAnswer, memberService);


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(
            @PathVariable("answer-id") @Positive long answerId,
            @Valid @RequestBody AnswerDto.Patch requestBody) {
        requestBody.setAnswerId(answerId);

        Answer answer =
                answerService.updateAnswer((mapper.answerPatchToAnswer(requestBody)));


        Member findMember = memberService.findVerifiedMember(answer.getMember().getMemberId());
        checkAuthMember(findMember.getEmail());


        AnswerDto.Response response = mapper.answerToAnswerResponse(answer, memberService);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public void checkAuthMember(String email) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!email.equals(username)) throw new BusinessLogicException(ExceptionCode.MEMBER_INCONSISTENCY);
    }
}
