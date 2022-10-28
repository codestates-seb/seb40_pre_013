package com.sebbe013.stackoverflowclone.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebbe013.stackoverflowclone.member.dto.MemberSignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원가입() throws Exception {
        //given
        MemberSignUpDto newMember = MemberSignUpDto.builder().displayName(null).email("dmfke@gmail.com").password("sdfesdf").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(newMember);
        //when
        //then
        mockMvc.perform(post("/member")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.displayName").value("테스트"))
                .andDo(print());
    }


}