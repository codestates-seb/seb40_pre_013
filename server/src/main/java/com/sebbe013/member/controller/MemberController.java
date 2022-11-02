package com.sebbe013.member.controller;

import com.sebbe013.member.dto.MemberResposeDto;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.mapper.MemberMapper;
import com.sebbe013.member.service.Logout;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;
    private final Logout logout;

    // 회원가입
    @PostMapping("/members")
    public ResponseEntity signUpMember( @Valid @RequestBody MemberSignUpDto memberSignUpDto ){
        log.info("회원가입 시작");
        Member member = mapper.memberSignUpDtotoMember(memberSignUpDto); //멤버 dto 멤버 객체로 변환
        log.info("role = {}", member.getRoles());
        memberService.joinMember(member); //회원가입 실행 메서드
        MemberResposeDto memberResposeDto = mapper.memberToResponse(member);

        log.info("회원가입 완료");

        return new ResponseEntity<>(memberResposeDto,HttpStatus.CREATED);
    }

    //로그아웃
    @GetMapping("members/logout")
    public String logout( HttpServletRequest request ){
        logout.logout(request);//로그아웃 서비스 메서드
        return "로그아웃 되었습니다.";
    }

//    @GetMapping("/members")
//    public String a(){
//        return "asdf";
//    }
}
