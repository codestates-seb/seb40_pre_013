package com.sebbe013.answer.entity;

import com.sebbe013.audit.Auditable;
import com.sebbe013.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter // 리팩토링 가능성 O, 엔티티에는 Setter 사용 지양
@Getter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

//    @ManyToOne
//    @JoinColumn(name = "QUESTION_ID")
//    private Question question;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String answerContent;
}