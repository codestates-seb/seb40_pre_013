package com.sebbe013.answer.service;

import com.sebbe013.answer.entity.Answer;
import com.sebbe013.answer.repository.AnswerRepository;
import com.sebbe013.exception.bussiness.BusinessLogicException;
import com.sebbe013.exception.bussiness.ExceptionCode;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final MemberService memberService;

    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, MemberService memberService, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.memberService = memberService;
        this.questionService = questionService;
    }

    public Answer createAnswer(Answer answer) {
        // 회원 확인
        log.info("답변 생성");
        Member findMember = memberService.findVerifiedMember(answer.getMember().getMemberId());
        questionService.findVerifiedQuestion(answer.getQuestion().getQuestionId());
        Answer savedAnswer = answerRepository.save(answer);

        return savedAnswer;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE) // 트랜잭션이 완료될 때까지 다른 사용자는 그 영역에 해당되는 데이터에 대한 수정 및 입력이 불가능
    public Answer updateAnswer(Answer answer) {

        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getAnswerContent())
                .ifPresent(answerContent -> findAnswer.setAnswerContent(answerContent));
        log.info("답변 수정 완료");
        return answerRepository.save(answer);
    }

    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    // questionId에 해당하는 답변목록 조회
    public List<Answer> findAnswers(long questionId) {
        return answerRepository.findByQuestion(questionId);
    }

    public void deleteAnswer(long answerId) {
        log.info("답변 삭제");
        Answer findAnswer = findVerifiedAnswer(answerId);
        answerRepository.delete(findAnswer);
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId) {
        log.info("유효한 답변 확인");
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }
}
