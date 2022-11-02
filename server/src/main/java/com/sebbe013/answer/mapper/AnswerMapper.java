package com.sebbe013.answer.mapper;

import com.sebbe013.answer.dto.AnswerDto;
import com.sebbe013.answer.entity.Answer;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostToAnswer(AnswerDto.Post requestBody) {
        Answer answer = new Answer();
        Member member = new Member();
        member.setMemberId(requestBody.getMemberId());
        answer.setMember(member);
        answer.setAnswerContent(requestBody.getAnswerContent());

        return answer;
    }
    default Answer answerPatchToAnswer(AnswerDto.Patch requestBody) {
        Answer answer = new Answer();
        Member member = new Member();
        member.setMemberId(requestBody.getMemberId());
        answer.setAnswerId(requestBody.getAnswerId());
        answer.setMember(member);
        answer.setAnswerContent(requestBody.getAnswerContent());

        return answer;
    }

    default AnswerDto.Response answerToAnswerResponse(Answer answer, MemberService memberService) {
        AnswerDto.Response answerResponse = new AnswerDto.Response();

        Member member = memberService.findVerifiedMember(answer.getMember().getMemberId());//

        answerResponse.setAnswerId(answer.getAnswerId());
        answerResponse.setWriter(member.getDisplayName());
        answerResponse.setAnswerContent(answer.getAnswerContent());
        answerResponse.setCreateAt(answer.getCreatedAt());
        answerResponse.setModifiedAt(answer.getModifiedAt());

        return answerResponse;
    }

    List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers); // 질문 조회 화면에서 사용??
}
