package com.sebbe013.answer.repository;

import com.sebbe013.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // 하나의 질문에 해당하는 답변 리스트 조회
    @Query(value = "SELECT * FROM Answer WHERE question_Id = :questionId", nativeQuery = true)
    List<Answer> findByQuestion(long questionId);
}
