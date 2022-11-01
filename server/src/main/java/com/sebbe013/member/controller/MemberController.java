package com.sebbe013.member.controller;

import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.mapper.MemberMapper;
import com.sebbe013.member.repository.MemberRepository;
import com.sebbe013.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/api")
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public ResponseEntity<HttpStatus> signUpMember( @Valid @RequestBody MemberSignUpDto memberSignUpDto ){
        log.info("회원가입 시작");
        Member member = memberMapper.memberSignUpDtoToMember(memberSignUpDto);
        memberService.encodePassword(member);
        log.info("권한 부여 시작");
        memberService.createRole(member);
        log.info("role = {}", member.getRoles());
        memberRepository.save(member);
        log.info("회원가입 완료");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/answers")
    public String ancs(){
        return "답변";
    }

    @GetMapping("/members")
    public String sd(){
        return "dfdsf";
    }


}
