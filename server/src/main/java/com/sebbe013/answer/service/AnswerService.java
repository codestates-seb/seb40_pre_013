package com.sebbe013.answer.service;

import com.sebbe013.answer.entity.Answer;
import com.sebbe013.answer.repository.AnswerRepository;
import com.sebbe013.exception.BusinessLogicException;
import com.sebbe013.exception.ExceptionCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional  // 학습
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {

        // 검증 코드 추가 구현 필요성? 회원, 질문 확인?
        Answer savedAnswer = answerRepository.save(answer);

        return savedAnswer;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE) // 트랜잭션이 완료될 때까지 다른 사용자는 그 영역에 해당되는 데이터에 대한 수정 및 입력이 불가능
    public Answer updateAnswer(Answer answer) {

        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getAnswerContent())
                .ifPresent(answerContent -> findAnswer.setAnswerContent(answerContent));

//        answer.setAnswerContent(answer.getAnswerContent());  // setter 사용 리팩토링 가능성

        return answerRepository.save(answer);
    }

    public void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        answerRepository.delete(findAnswer);
    }

    @Transactional(readOnly = true)
    private Answer findVerifiedAnswer(long answerId) {

        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer =
                optionalAnswer.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
        return findAnswer;
    }

    private void verifyAnswerWriter(long answerId) {

    }

    public void checkAuthMember(String email) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!email.equals(username)) throw new BusinessLogicException(ExceptionCode.MEMBER_INCONSISTENCY);
    }
}
