package com.sebbe013.login.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.member.dto.LoginResponseDto;
import com.sebbe013.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class MemberAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException{
        log.info("로그인 성공");

        Member member = (Member) authentication.getPrincipal();

        LoginResponseDto id = LoginResponseDto.builder().id(member.getMemberId()).build();
        ObjectMapper objectMapper = new ObjectMapper();

        String ids = objectMapper.writeValueAsString(id);

        response.getWriter().write(ids);
    }

}

