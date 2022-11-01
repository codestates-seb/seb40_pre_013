package com.sebbe013.question.entity;

// TODO: import 문 추가 필요 (Member, Answer. Auditable)

import com.sebbe013.answer.entity.Answer;
import com.sebbe013.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.AbstractFutureOrPresentInstantBasedValidator;

import javax.persistence.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
// TODO: Auditable 상속하기
// 질문 클래스
public class Question {
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

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();       // 답변 리스트

    // 질문에 작성자 추가
    public void addMember(Member member) {
        this.member = member;
    }

    // 질문에 답변 추가
    public void addAnswer(Answer answer) {
        answers.add(answer);

    }
}
