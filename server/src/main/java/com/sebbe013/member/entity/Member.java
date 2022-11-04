package com.sebbe013.member.entity;

import com.sebbe013.answer.entity.Answer;
import com.sebbe013.audit.Auditable;
import com.sebbe013.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Slf4j
@Entity
//수정일은 나중에 결
public class Member extends Auditable implements  Principal, UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false, unique = true)
    private String displayName;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    public void updateMemberId(Long memberId){
        this.memberId = memberId;
    }

    public void updatePassword( String encodingPassword ){
        this.password = encodingPassword;
        log.info("비밀번호 암호화");
    }

    public void updateRoles( List<String> checkedRoles ){
        this.roles = checkedRoles;
        log.info("권한 부여");
    }

    @Override
    public String getName(){
        return this.email;
    }


    ///////// 여기 아래부터 권한 메서드

    //권한 부여 후 리턴하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> auth = getRoles().stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());
        log.info("auth = {}",auth);
        return auth;
    }

    @Override
    public String getUsername(){
        return this.email;
    } // username = email

    @Override
    public boolean isAccountNonExpired(){
        return true;
    } // 없어진 계정 확인 true면 만료 안됨

    @Override
    public boolean isAccountNonLocked(){
        return true;
    } // 잠긴 계정인지 확인

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }//암호가 만료된 계정인지

    @Override
    public boolean isEnabled(){
        return true;
    }//현재 이용 가능한 계정인지
}
