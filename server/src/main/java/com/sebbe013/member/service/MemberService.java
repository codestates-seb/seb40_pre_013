package com.sebbe013.member.service;

import com.sebbe013.exception.ExistDisplayNameExeption;
import com.sebbe013.exception.ExistEamil;
import com.sebbe013.login.auth.AuthUtils;
import com.sebbe013.login.filter.JwtVerificationFilter;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    private final JwtVerificationFilter jwtVerificationFilter;

    public void joinMember( Member member ){
        log.error("회원가입 시작");
        checkExistEmail(member.getEmail());
        checkExistDisplayName(member.getDisplayName());
        encodePassword(member);
        createRole(member);
        memberRepository.save(member);
    }
    //토큰에서 id 추출
    public Long findMemberId( HttpServletRequest request ){
        log.info("토큰에서 id 찾기");
        Map<String, Object> claims = jwtVerificationFilter.verifyJws(request);
        long memberId = ((Number) claims.get("memberId")).longValue();

        log.info("memberId = {}", memberId);

        return memberId;
    }

    //비밀번호 알고리즘 암호화 메서드
    private Member encodePassword( Member member ){

        String encodingPassword = passwordEncoder.encode(member.getPassword());
        member.encryptedPassword(encodingPassword);

        log.info("암호화 완료");
        return member;
    }

    //권한 부여 메서드
    private Member createRole( Member member ){
        List<String> checkedRoles = authUtils.createRole(member.getEmail());

        member.giveRoles(checkedRoles);

        log.info("권한 부여 완료");

        return member;
    }

    //이메일 중복 확인
    private void checkExistEmail( String email ){
        log.info("이메일 중복 확인");

        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) throw new ExistEamil();
    }
    //diaplay name 중복 확인
    private void checkExistDisplayName( String displayName ){
        log.info("diplay name 중복 확인");

        Optional<Member> member = memberRepository.findByDisplayName(displayName);
        if(member.isPresent()) throw new ExistDisplayNameExeption();
    }
}
