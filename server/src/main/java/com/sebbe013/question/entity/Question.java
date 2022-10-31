package com.sebbe013.question.entity;

// TODO: import 문 추가 필요 (Member, Answer. Auditable)

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
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

    @JoinColumn(name = "MEMBER_ID")
    private Member member;              // 질문 작성자

    // TODO: 무엇으로 mappedBy 해야할지 확인 필요 (answer에서)
    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();       // 답변 리스트

}
