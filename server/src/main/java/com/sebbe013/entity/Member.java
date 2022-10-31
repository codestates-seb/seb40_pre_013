package com.sebbe013.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Slf4j
//수정일은 나중에 결
public class Member implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 20, name = "display_name", nullable = false, unique = true)
    private String displayName;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public void encryptedPassword( String encodingPassword ){
        this.password = encodingPassword;
        log.info("비밀번호 암호화");
    }

    public void giveRoles( List<String> checkedRoles ){
        this.roles = checkedRoles;
        log.info("권한 부여");
    }

    @Override
    public String getName(){
        return this.email;
    }

//    @OneToMany(mappedBy = "member")
//    private List<Question> questions = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Answer> answers = new ArrayList<>();

}
