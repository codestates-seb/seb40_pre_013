package com.sebbe013.answer.entity;

import com.sebbe013.audit.Auditable;
import com.sebbe013.member.entity.Member;
import com.sebbe013.question.entity.Question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String answerContent;

    public void updateAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void updateQuestion(Question question) {
        this.question = question;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}