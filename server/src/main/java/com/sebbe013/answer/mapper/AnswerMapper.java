package com.sebbe013.answer.mapper;

import com.sebbe013.answer.dto.AnswerDto;
import com.sebbe013.answer.entity.Answer;
import com.sebbe013.exception.bussiness.BusinessLogicException;
import com.sebbe013.exception.bussiness.ExceptionCode;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.entity.Question;
import org.mapstruct.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostToAnswer(AnswerDto.Post requestBody, MemberService memberService, HttpServletRequest httpServletRequest) {
        Answer answer = new Answer();
        Member member = new Member();
        Question question = new Question();

        question.setQuestionId(requestBody.getQuestionId());
        answer.updateQuestion(question);

        member.setMemberId(memberService.findMemberId(httpServletRequest));
        answer.updateMember(member);

        answer.updateAnswerContent(requestBody.getAnswerContent());

        return answer;
    }
    default Answer answerPatchToAnswer(AnswerDto.Patch requestBody, MemberService memberService, HttpServletRequest httpServletRequest, long questionId) {
        Answer answer = new Answer();
        Member member = new Member();

        member.setMemberId(memberService.findMemberId(httpServletRequest));
        answer.updateMember(member);

        answer.updateAnswerId(requestBody.getAnswerId());
        answer.updateAnswerContent(requestBody.getAnswerContent());

        Question question = new Question();
        question.setQuestionId(questionId);
        answer.updateQuestion(question);
        return answer;
    }

    default AnswerDto.Response answerToAnswerResponse(Answer answer, MemberService memberService) {
        Member member = memberService.findVerifiedMember(answer.getMember().getMemberId());

        return AnswerDto.Response
                .builder()
                .answerId(answer.getAnswerId())
                .writer(member.getDisplayName())
                .answerContent(answer.getAnswerContent())
                .modifiedAt(answer.getModifiedAt())
                .build();
    }

    // 질문 조회 화면에서 사용
    default List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers) {

        return answers.stream()
                .map(answer -> AnswerDto.Response
                        .builder()
                        .answerId(answer.getAnswerId())
                        .writer(answer.getMember().getDisplayName())
                        .answerContent(answer.getAnswerContent())
                        .modifiedAt(answer.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
