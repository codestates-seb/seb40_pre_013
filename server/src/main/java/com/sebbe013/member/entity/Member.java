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
@Setter
@Entity
@Slf4j
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

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    /////////

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
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
