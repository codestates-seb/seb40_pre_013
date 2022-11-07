package com.sebbe013.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.member.dto.LoginDto;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.mapper.MemberMapper;
import com.sebbe013.member.repository.MemberRepository;
import com.sebbe013.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class LoginTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper mapper;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init(){
        memberRepository.deleteAll();
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("123@aadfend4").build();
        Member member = mapper.memberSignUpDtotoMember(memberDto1);
        memberService.joinMember(member);
    }

    @Test
    void 로그인_성공() throws Exception{
        //given
        LoginDto loginDto = LoginDto.builder().username("test@gmail.com").password("123@aadfend4").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(loginDto);

        mockMvc.perform(post("/members/login").contentType(MediaType.APPLICATION_JSON).content(dto))
                .andExpect(status().isOk())
                .andExpect(content().string("로그인 성공"))
                .andDo(print());

    }

    @Test
    @DisplayName("아이디가 틀렸을 때 예외처리")
    void 로그인_실패() throws Exception {
        LoginDto loginDto = LoginDto.builder().username("tst@gmail.com").password("123@aadfend4").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(loginDto);

        mockMvc.perform(post("/members/login").contentType(MediaType.APPLICATION_JSON).content(dto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.message").value("Unauthorized"))
                .andDo(print());
    }
    @Test
    @DisplayName("비밀전호가 틀렸을 때 예외처리")
    void 로그인_실패2() throws Exception {
        LoginDto loginDto = LoginDto.builder().username("tst@gmail.com").password("123@aadfend4").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(loginDto);

        mockMvc.perform(post("/members/login").contentType(MediaType.APPLICATION_JSON).content(dto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.message").value("Unauthorized"))
                .andDo(print());
    }


}
