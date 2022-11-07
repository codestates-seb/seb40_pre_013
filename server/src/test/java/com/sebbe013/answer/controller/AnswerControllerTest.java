package com.sebbe013.answer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.answer.dto.AnswerDto;
import com.sebbe013.member.dto.LoginDto;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.mapper.MemberMapper;
import com.sebbe013.member.repository.MemberRepository;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.question.dto.QuestionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class AnswerControllerTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberMapper mapper;
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
    void 답변_포스트() throws Exception{
        //given
        String authorization = auth(); //로그인했구요

        QuestionDto.Post dto = QuestionDto.Post.builder().questionTitle("제목입니다.").questionContent("내용입니다.").memberId(1L).build(); //퀘스쳔디티오를 생성

        ObjectMapper objectMapper = new ObjectMapper();
        String dtos = objectMapper.writeValueAsString(dto);//디티오를 json형태로 만듦

        //질문포스트진행
        mockMvc.perform(post("/questions")
                                .header("Authorization",authorization)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dtos));

        //when
        AnswerDto.Post answerDto
                = AnswerDto.Post.builder().answerContent("답변입니다.").questionId(1L).build(); //답변디티오 생성
        String adto = objectMapper.writeValueAsString(answerDto);//제이슨형태

        mockMvc.perform(post("/answers") //답변포스트
                                .header("Authorization",authorization)//토큰
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(adto))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.answerId").value(1))
                .andExpect(jsonPath("$.writer").value("test"))
                .andExpect(jsonPath("$.answerContent").value("답변입니다."))
                .andDo(print());




        //then
    }

    private String auth() throws Exception{
        //given
        LoginDto loginDto = LoginDto.builder().username("test@gmail.com").password("123@aadfend4").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String dto = objectMapper.writeValueAsString(loginDto);

        ResultActions actions = mockMvc.perform(post("/members/login").contentType(MediaType.APPLICATION_JSON).content(dto));


        String authorization = actions.andReturn().getResponse().getHeader("Authorization");
        return authorization;
    }
}