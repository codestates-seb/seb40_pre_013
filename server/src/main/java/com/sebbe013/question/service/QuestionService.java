package com.sebbe013.question.service;

import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.entity.Question;
import com.sebbe013.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

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
}
