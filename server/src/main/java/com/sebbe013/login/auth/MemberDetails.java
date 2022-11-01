package com.sebbe013.login.auth;

import com.sebbe013.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
//회원 정보, 권한 확인 클래스
@Slf4j
public class MemberDetails extends Member implements UserDetails {

    MemberDetails(Member member){
        setMemberId(member.getMemberId());
        setRoles(member.getRoles());
        setPassword(member.getPassword());
        setEmail(member.getEmail());
        setDisplayName(member.getDisplayName());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        log.info("권한 확인");
        List<GrantedAuthority> auth = getRoles().stream().
                map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());
        log.info("auth = {}",auth);
        return auth;
    }



    @Override
    public String getUsername(){
        return getEmail();
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
