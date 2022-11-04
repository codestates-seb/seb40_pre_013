package com.sebbe013.question.mapper;

import com.sebbe013.member.entity.Member;
import com.sebbe013.question.dto.QuestionDto;
import com.sebbe013.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
//    Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto);
//    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);
//    QuestionDto.Response questionToQuestionResponseDto(Question question);

    default Question questionPostDtoToQuestion(QuestionDto.Post questionPostDto) {
        // 제목, 내용으로 질문 객체 생성
        Question question = new Question(questionPostDto.getQuestionTitle(),
                                        questionPostDto.getQuestionContent());

        // 질문 작성자
        Member member = new Member();
        member.updateMemberId(questionPostDto.getMemberId());

        // 질문에 작성자 추가
        question.addMember(member);

        return question;
    }
    default Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto){
        // 질문 생성
        Question question = new Question(questionPatchDto.getQuestionTitle(),
                                        questionPatchDto.getQuestionContent());
        question.updateQuestionId(questionPatchDto.getQuestionId());           // 질문 ID 설정

        Member member = new Member();                           // 질문 수정자(멤버) 생성
        member.setMemberId(questionPatchDto.getMemberId());     // 질문 수정자 ID 설정

        question.addMember(member);                             // 질문에 수정자 추가

        return question;
    }
    default QuestionDto.Response questionToQuestionResponseDto(Question question){

        // Question Response Dto 객체 생성
        QuestionDto.Response questionResponseDto = QuestionDto.Response.builder()
                        .questionId(question.getQuestionId())               // 질문 id
                        .questionTitle(question.getQuestionTitle())         //질문 제목
                        .questionContent(question.getQuestionContent())     // 질문 내용
                        .memberId(question.getMember().getMemberId())       // 질문 작성자 id
                        .questionWriter(question.getMember().getDisplayName())  // 질문 작성자 display name
                        .createdAt(question.getCreatedAt())     // 질문 작성 날짜
                        .modifiedAt(question.getModifiedAt())
                        .build();

        return questionResponseDto;
    }
}
