package com.sebbe013.stackoverflowclone.member.controller;

import com.sebbe013.stackoverflowclone.member.dto.MemberSignUpDto;
import com.sebbe013.stackoverflowclone.member.entity.Member;
import com.sebbe013.stackoverflowclone.member.mapper.MemberMapper;
import com.sebbe013.stackoverflowclone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping
    public Member signUpMember( @Valid @RequestBody MemberSignUpDto memberSignUpDto){
        Member member = memberMapper.memberSignUpDtoToMember(memberSignUpDto);
        memberService.encodePassword(member);
        memberService.createMember(member);
        return member;
    }
}
