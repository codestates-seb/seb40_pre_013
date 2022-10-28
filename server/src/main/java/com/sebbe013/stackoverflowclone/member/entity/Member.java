package com.sebbe013.stackoverflowclone.member.entity;

import com.sebbe013.stackoverflowclone.member.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
//수정일은 나중에 결
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 20, name = "display_name", nullable = false, unique = true)
    private String displayName;

    @Column( nullable = false)
    private String password;

    //    @OneToMany(mappedBy = question)
//    private List<Question> questions = new ArrayList<>();
//
//   @OneToMany(mappedBy = answer)
//   private List<Answer> answers = new ArrayList<>();

}
