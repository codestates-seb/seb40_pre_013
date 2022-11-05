package com.sebbe013.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class MemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberMapper mapper;
    @Autowired
    private MemberService memberService;
    @BeforeEach
    void init(){
        memberRepository.deleteAll();
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test3@gmail.com").displayName("test3").password("12333@aadfend4").build();
        Member member = mapper.memberSignUpDtotoMember(memberDto1);
        memberService.joinMember(member);
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
    void 이미_존재하는_이메일() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test3@gmail.com").displayName("test3").password("12333@aadfend4").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);

        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("이미 가입한 e-mail입니다."))
                .andDo(print());
    }
    @Test
    void 이미_존재하는_닉네임() throws Exception{
        //given
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test1@gmail.com").displayName("test3").password("12333@aadfend4").build();
        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(memberDto1);

        //then
        mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(member))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("이미 존재하는 닉네임입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("이메일 특수 문자 사용 불가")
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
    @DisplayName("비밀번호 8자리 이하 예외처리")
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
    @DisplayName("비밀번호 특수 문자 제외 예외처리")
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
    @DisplayName("비밀번호 숫자 제외 예외처리")
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
    @DisplayName("비밀전호 영문자 제외 예외처리")
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