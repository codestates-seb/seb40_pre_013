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
        Question question = new Question();
        question.setQuestionTitle(questionPostDto.getQuestionTitle());
        question.setQuestionContent(questionPostDto.getQuestionContent());


        // 질문 작성자
        Member member = new Member();
        member.setMemberId(questionPostDto.getMemberId());

        // 질문에 작성자 추가
        question.addMember(member);

        return question;
    }
    default Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto){
        // 질문 생성
        Question question = new Question();
        question.setQuestionId(questionPatchDto.getQuestionId());           // 질문 ID 설정
        question.setQuestionTitle(questionPatchDto.getQuestionTitle());     // 질문 제목 설정
        question.setQuestionContent(questionPatchDto.getQuestionContent()); // 질문 내용 설정

        Member member = new Member();                           // 질문 수정자(멤버) 생성
        member.setMemberId(questionPatchDto.getMemberId());     // 질문 수정자 ID 설정

        question.addMember(member);          // 질문에 수정자 추가

        return question;
    }
    default QuestionDto.Response questionToQuestionResponseDto(Question question){

        QuestionDto.Response questionResponseDto = new QuestionDto.Response();

        // 질문 id를 question에서 가져와서 RespnoseDto에 저장
        questionResponseDto.setQuestionId(question.getQuestionId());
        //질문 제목을 question에서 가져와서 RespnoseDto에 저장
        questionResponseDto.setQuestionTitle(question.getQuestionTitle());
        // 질문 내용을 question에서 가져와서 RespnoseDto에 저장
        questionResponseDto.setQuestionContent(question.getQuestionContent());

        // 질문 작성자 id, display name를 question의 member에서 가져와서 ResponseDto에 저장
        questionResponseDto.setMemberId(question.getMember().getMemberId());
        questionResponseDto.setQuestionWriter(question.getMember().getDisplayName());

        // 질문 작성,수정날짜를 question에서 가져와서 ResponseDto에 저장
        questionResponseDto.setCreatedAt(question.getCreatedAt());
        questionResponseDto.setModifiedAt(question.getModifiedAt());

        return questionResponseDto;
    }
}
