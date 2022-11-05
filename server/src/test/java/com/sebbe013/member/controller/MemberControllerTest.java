package com.sebbe013.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.repository.MemberRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach
    void init(){
        memberRepository.deleteAll();
    }
    @Test
    void 회원_가입() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("123@aadfend4").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);
        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(jsonPath("$.email").value("test@gmail.com"))
                .andExpect(jsonPath("$.displayName").value("test"))
                .andExpect(jsonPath("$.roles").value("USER"))
                .andDo(print());
    }

    @Test
    @DisplayName("이메일 특수 문자 사용")
    void 이메일_유효성_검사() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("tes^t@gmail.com").displayName("test").password("12aa!!!!!634").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);

        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0:1].field").value("email"))
                .andExpect(jsonPath("$.fieldErrors[0:1].rejectedValue").value("tes^t@gmail.com"))
                .andDo(print());
    }

    @Test
    @DisplayName("비밀번호 8자리 이하")
    void 패스워드_유효성_검사1() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("12@a123").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);

        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0:1].field").value("password"))
                .andExpect(jsonPath("$.fieldErrors[0:1].rejectedValue").value("12@a123"))
                .andDo(print());
    }

    @Test
    @DisplayName("비밀번호 특수 문자 제외")
    void 패스워드_유효성_검사2() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("12dddda123").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);

        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0:1].field").value("password"))
                .andExpect(jsonPath("$.fieldErrors[0:1].rejectedValue").value("12dddda123"))
                .andDo(print());
    }

    @Test
    @DisplayName("비밀번호 숫자 제외")
    void 패스워드_유효성_검사3() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("@@@@@adddda").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);

        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0:1].field").value("password"))
                .andExpect(jsonPath("$.fieldErrors[0:1].rejectedValue").value("@@@@@adddda"))
                .andDo(print());
    }

    @Test
    @DisplayName("영문자 숫자 제외")
    void 패스워드_유효성_검사4() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("@@@@@123123123").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);

        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors[0:1].field").value("password"))
                .andExpect(jsonPath("$.fieldErrors[0:1].rejectedValue").value("@@@@@123123123"))
                .andDo(print());
    }


}