package com.sebbe013.stackoverflowclone.member.service;

import com.sebbe013.stackoverflowclone.member.entity.Member;
import com.sebbe013.stackoverflowclone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * 아이디 중복
 * 닉네임 중복
 */
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember( Member member ){
        memberRepository.save(member);
    }
}
