package com.sebbe013.member.login.auth.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
로그인 성공 시 마지막에 오는 클래스
 */
@Slf4j
@RequiredArgsConstructor
public class MemberAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException{
        log.info("로그인 성공");
//         바디에 멤버 아이디 넣어줌 테스트용
//        Member member = (Member) authentication.getPrincipal();
//
//        LoginResponseDto id = LoginResponseDto.builder().id(member.getMemberId()).build();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String ids = objectMapper.writeValueAsString(id);
//
//        response.getWriter().write(ids);
    }

}

