package com.sebbe013.member.service;

import com.sebbe013.login.auth.AuthUtils;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 아이디 중복
 * 닉네임 중복
 */
@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtils authUtils;

    //비밀번호 알고리즘 암호화 메서드
    public Member encodePassword( Member member ){
        log.info("비밀번호 암호화 시작");
        String encodingPassword = passwordEncoder.encode(member.getPassword());
        log.info("password = {}",encodingPassword);
        member.encryptedPassword(encodingPassword);
        log.info("암호화 완료");
        return member;
    }
//권한 부여 메서드
    public Member createRole(Member member){

        List<String> checkedRoles = authUtils.createRole(member.getEmail());
        member.giveRoles(checkedRoles);
        log.info("권한 부여 완료");
        return member;
    }
}
