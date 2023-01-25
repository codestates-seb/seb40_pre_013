package com.sebbe013.question.service;

import com.sebbe013.exception.bussiness.BusinessLogicException;
import com.sebbe013.exception.bussiness.ExceptionCode;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.entity.Question;
import com.sebbe013.question.repository.QuestionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    /**
     * 질문 등록
     * @param question - 등록하고자 하는 질문
     * @return Question - 등록된 질문
     */
    public Question createQuestion(Question question) {
        // MemberService에서 작성자가 멤버로 존재하는지 확인
        Member member = memberService.findVerifiedMember(question.getMember().getMemberId());

        // 해당 멤버를 질문의 작성자로 설정
        question.addMember(member);

        // questionRepository에 저장
        return questionRepository.save(question);
    }

    /**
     * 질문 수정
     * @param question - 수정할 질문
     * @return Question - 수정된 질문
     */
    public Question updateQuestion(Question question) {
        // MemberService에서 작성자가 멤버로 존재하는지 확인
        Member member = memberService.findVerifiedMember(question.getMember().getMemberId());

        // Question Repository에서 Question Id로 검색해 존재하는 질문인지 확인
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        // question id로 검색한 Question Repository에 저장된 질문의  작성자 id
        long findMemberId = findQuestion.getMember().getMemberId();

        // 클라이언트가 요청한 질문의 클라이언트 ID
        long requestMemberId = question.getMember().getMemberId();

        // 저장된 질문의 작성자와 삭제 요청한 클라이언트가 같은지 확인
        verifyQuestionWriter(findMemberId, requestMemberId);

        // 질문 제목 변경
        Optional.ofNullable(question.getQuestionTitle())
                .ifPresent(questionTitle -> findQuestion.updateQuestionTitle(questionTitle));
        // 질문 내용 변경 
        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(questionContent -> findQuestion.updateQuestionContent(questionContent));

        // Repository에 질문 저장 후 변환
        return questionRepository.save(findQuestion);
    }

    /**
     * 질문id를 이용해 QuestionRepository에 존재하는 질문인지 확인
     * @param questionId - 확인하고자 하는 질문 id
     * @return Qusetion - qusetion Repository에 저장된 해당 id 가진 질문
     */
    public Question findVerifiedQuestion(long questionId) {
        // questionRepository에서 questionId로 질문 검색
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    /**
     * 질문 하나 조회
     * @param questionId - 선택한 질문 id
     * @return Question - Question repository에 존재하는 해당 id를 가진 질문
     */
    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);
    }

    // 전체 질문 목록 조회
    public List<Question> findQuestions() {
        // QuestionRepository에서 모든 질문 조회하여 작성날짜 내림차순으로 정렬후 반환
        return questionRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    /**
     * 질문 하나 삭제
     * @param questionId - 삭제하고싶은 질문 id
     * @param memberId - 삭제 요청한 클라이언트의 id
     */
    public void deleteQuestion(long questionId, long memberId) {
        // MemberService에서 작성자가 멤버로 존재하는지 확인
        Member member = memberService.findVerifiedMember(memberId);

        // question Repository에서 존재하는 유효한 질문인지 가져오기 
        Question findQuestion = findVerifiedQuestion(questionId);

        // question id로 검색한 Question Repository에 저장된 질문의  작성자 id
        long findMemberId = findQuestion.getMember().getMemberId();

        // 저장된 질문의 작성자와 삭제 요청한 클라이언트가 같은지 확인
        verifyQuestionWriter(findMemberId, memberId);

        questionRepository.delete(findQuestion);
    }

    /**
     * 저장된 질문의 작성자 id와 클라이언트가 요청한 질문의 작성자id가 같은지 확인
     * @param findMemberId - repository에 저장된 질문의 작성자 id
     * @param requestMemberId - 클라이언트가 요청한 질문의 작성자 id
     */
    public void verifyQuestionWriter(long findMemberId, long requestMemberId) {
        if (findMemberId != requestMemberId)   // 저장된 질문의 작성자의 id와 요청보낸 클라이언트의 id가 다르면
            throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_ALLOWED);       // 권한 없음
    }
}
