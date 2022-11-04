package com.sebbe013.member.service;

import com.sebbe013.exception.bussiness.BusinessLogicException;
import com.sebbe013.exception.bussiness.ExceptionCode;
import com.sebbe013.member.entity.Member;
import com.sebbe013.login.auth.AuthUtils;
import com.sebbe013.login.filter.JwtVerificationFilter;
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

    //멤버 정보 저장 메서드

    public Member joinMember( Member member ){
        log.error("회원가입 시작");
        checkExistEmail(member.getEmail()); // 이메일 중복확인
        checkExistDisplayName(member.getDisplayName());// 디스플레이 중복 확인
        encodePassword(member); // 비밀번호 암호화
        createRole(member);//권한 부여
        memberRepository.save(member);//멤버 정보 저장
        return  member;
    }

    //토큰에서 id 추출 메서드
    public Long findMemberId( HttpServletRequest request ){
        log.info("토큰에서 id 찾기");
        Map<String, Object> claims = jwtVerificationFilter.verifyJws(request); //jwt가 올바르면 클레임 추출
        long memberId = ((Number) claims.get("memberId")).longValue();//Integer형 Long형으로 변환후 memberid 추출

        log.info("memberId = {}", memberId);

        return memberId;
    }

    //비밀번호 알고리즘 암호화 메서드
    private Member encodePassword( Member member ){

        String encodingPassword = passwordEncoder.encode(member.getPassword()); //일반 비밀번호 암호화
        member.updatePassword(encodingPassword);//암호화한 패스워드 멤버 필드에 저장

        log.info("암호화 완료");
        return member;
    }

    //역할? 생성 메서드
    private Member createRole( Member member ){
        List<String> checkedRoles = authUtils.createRole(member.getEmail()); //멤버 이메일에 따른 권한 생성

        member.updateRoles(checkedRoles);//생성된 권한 멤버 필드에 저장

        log.info("권한 부여 완료");

        return member;
    }

    //이메일 중복 확인
    private void checkExistEmail( String email ){
        log.info("이메일 중복 확인");

        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) throw new BusinessLogicException(ExceptionCode.EXIST_EMAIL);
    }

    //diaplay name 중복 확인
    private void checkExistDisplayName( String displayName ){
        log.info("diplay name 중복 확인");

        Optional<Member> member = memberRepository.findByDisplayName(displayName);
        if(member.isPresent()) throw new BusinessLogicException(ExceptionCode.EXIST_DISPALY_NAME);
    }


    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }
  }
