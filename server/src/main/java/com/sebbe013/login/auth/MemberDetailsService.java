package com.sebbe013.login.auth;

import com.sebbe013.member.entity.Member;
import com.sebbe013.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
/*
로그인 진행 클래스
 */
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException{
        log.info("로그인 진행 함수");
        Optional<Member> entity = memberRepository.findByEmail(username);//username(이메일)을 이용해 회원이 있는지 확인

        log.info("entity = {}",entity);
        log.info("멤버 확인");
        return entity.orElseThrow(() -> new UsernameNotFoundException("")); //있으면 Userdetails형 객체 리턴
    }
}
