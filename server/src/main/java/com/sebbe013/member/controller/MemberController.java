package com.sebbe013.member.controller;

import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.mapper.MemberMapper;
import com.sebbe013.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/members")
    public ResponseEntity<HttpStatus> signUpMember( @Valid @RequestBody MemberSignUpDto memberSignUpDto ){
        log.info("회원가입 시작");
        Member member = memberMapper.memberSignUpDtoToMember(memberSignUpDto);
        log.info("role = {}", member.getRoles());
        memberService.joinMember(member);
        log.info("회원가입 완료");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

// id확인 완료
//    @GetMapping("/members")
//    public Long assd( HttpServletRequest request){
//        Long memberId = memberService.findMemberId(request);
//        return memberId;
//    }


}
