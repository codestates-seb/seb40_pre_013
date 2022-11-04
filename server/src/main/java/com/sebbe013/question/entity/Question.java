package com.sebbe013.question.entity;

import com.sebbe013.answer.entity.Answer;
import com.sebbe013.audit.Auditable;
import com.sebbe013.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
// 질문 클래스
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;            // 질문 id

    @Column(length = 100, nullable = false)
    private String questionTitle;       // 질문 제목

    @Column(length = 255, nullable = false)
    private String questionContent;     // 질문 내용

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;              // 질문 작성자

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)         // 질문 삭제시 답변도 삭제
    private List<Answer> answers = new ArrayList<>();       // 답변 리스트

    public Question(String questionTitle, String questionContent) {
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
    }

    // 질문 id
    public void updateQuestionId(long questionId) {
        this.questionId = questionId;
    }

    // 질문 제목 수정
    public void updateQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    // 질문 내용

    public void updateQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    // 질문에 작성자 추가
    public void addMember(Member member) {
        this.member = member;
    }

    // 질문에 답변 추가
    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
}