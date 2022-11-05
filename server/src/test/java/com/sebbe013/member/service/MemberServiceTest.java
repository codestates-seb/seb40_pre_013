package com.sebbe013.member.service;

import com.sebbe013.exception.bussiness.BusinessLogicException;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.mapper.MemberMapper;
import com.sebbe013.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MemberServiceTest {

    @Autowired
    private MemberMapper mapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void init(){
        memberRepository.deleteAll();
    }


    @Test
    void 회원가입_메서드() throws Exception{
        //given
        Member member = createMember("test2@gmail.com", "test1", "1234");
        memberService.joinMember(member);
        //when
        Member verifiedMember = memberService.findVerifiedMember(member.getMemberId());
        //then
        assertThat(verifiedMember.getEmail()).isEqualTo("test2@gmail.com");
        assertThat(verifiedMember.getDisplayName()).isEqualTo("test1");
    }

    @Test
    void 중복된_이메일_검증() throws Exception{//given
        Member member1 = createMember("test2@gmail.com", "test1", "1234");
        Member member2 = createMember("test2@gmail.com", "test2", "1234");
        //when
        memberService.joinMember(member1);
        //then
        BusinessLogicException e = assertThrows(BusinessLogicException.class, () -> {
            memberService.joinMember(member2);
        });

        assertThat(e.getExceptionCode().getMessage()).isEqualTo("이미 가입한 e-mail입니다.");
    }


    @Test
    void 중복된_닉네임_검증() throws Exception{
        //given
        Member member1 = createMember("test2@gmail.com", "test1", "1234");
        Member member2 = createMember("test3@gmail.com", "test1", "1234");

        //when
        memberService.joinMember(member1);
        //then
        BusinessLogicException e = assertThrows(BusinessLogicException.class, () -> {
            memberService.joinMember(member2);
        });
        assertThat(e.getExceptionCode().getMessage()).isEqualTo("이미 존재하는 닉네임입니다.");

    }

    @Test
    void 멤버_권한_user_검사() throws Exception{
        //given
        Member member = createMember("test2@gmail.com", "test1", "1234");
        //when
        Member findMember = memberService.joinMember(member);
        //then
        assertThat(findMember.getRoles().get(0)).isEqualTo("USER");
    }
    private Member createMember( String email, String displayName, String password ){
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email(email).displayName(displayName).password(password).build();
        return mapper.memberSignUpDtotoMember(memberDto1);
    }

}
