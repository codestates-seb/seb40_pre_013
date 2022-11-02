package com.sebbe013.question.service;

import com.sebbe013.exception.BusinessLogicException;
import com.sebbe013.exception.ExceptionCode;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.entity.Question;
import com.sebbe013.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    public QuestionService(QuestionRepository questionRepository,
                           MemberService memberService) {
        this.questionRepository = questionRepository;
        this.memberService = memberService;
    }

    /*
    질문 등록하는 메서드
    @param question: 질문 클래스
     */
    public Question createQuestion(Question question) {
        // MemberService에서 작성자가 멤버로 존재하는지 확인
        Member member = memberService.findVerifiedMember(question.getMember().getMemberId());
        // 해당 멤버를 질문의 작성자로 설정
        question.addMember(member);

        // questionRepository에 저장
        return questionRepository.save(question);
    }

    /*
    질문 업데이트 하는 메서드
    @param question  - 수정할 질문
     */
    public Question updateQuestion(Question question) {
        // 존재하는 질문인지 확인
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        // 질문 제목 변경
        Optional.ofNullable(question.getQuestionTitle())
                .ifPresent(questionTitle -> findQuestion.setQuestionTitle(questionTitle));
        // 질문 내용 변경 
        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(questionContent -> findQuestion.setQuestionContent(questionContent));

        // Repository에 질문 저장 후 변환
        return questionRepository.save(findQuestion);
    }

    /*
    질문id를 이용해 QuestionRepository에 존재하는 질문인지 확인하는 메서드
    @param questionId - 질문 id
     */
    public Question findVerifiedQuestion(long questionId) {
        // questionRepository에서 questionId로 질문 검색
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }
}