package com.sebbe013.member.controller;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //내장 서버를 랜덤 포트로 띄운다. 테스트를 위한 서블릿 컨테이너를 띄운다.
@ActiveProfiles("test")
public class LoginTest {
    @LocalServerPort //로컬 서버 포트를 사용한다.
    private int port;

    private RestTemplate restTemplate = new RestTemplate(); //랜덤 포트를 사용하면 resttemplate을 사용해야한다. 서버간 통신??

    private URI uri( String path ) throws URISyntaxException{ //uri를 생성한다.
        return new URI(format("http://localhost:%d%s", port, path));
    }

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper mapper;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init(){
        memberRepository.deleteAll();
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("123@aadfend4").build();
        Member member = mapper.memberSignUpDtotoMember(memberDto1);
        memberService.joinMember(member);
    }

    @Test
    void 로그인_시도() throws Exception{
        //given
        LoginDto loginDto = LoginDto.builder().username("test@gmail.com").password("123@aadfend4").build();
        HttpEntity<LoginDto> dto = new HttpEntity<>(loginDto);//logindto 내용이 바디값으로 들어간다??
        ResponseEntity<String> response = restTemplate.exchange(uri("/members/login"), HttpMethod.POST, dto, String.class);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

        System.out.println(response.getHeaders().get("Authorization"));
    }

    @Test
    @DisplayName("아이디가 틀렸을 때 예외처리")
    void 로그인_실패() throws Exception {
        //given
        LoginDto loginDto = LoginDto.builder().username("test1@gmail.com").password("123@aadfend4").build();
        HttpEntity<LoginDto> dto = new HttpEntity<>(loginDto);
        ResponseEntity<String> response = restTemplate.exchange(uri("/members/login"), HttpMethod.POST, dto, String.class);
        //then
        assertThat(response.getBody().contains(HttpStatus.UNAUTHORIZED.getReasonPhrase())).isTrue();
    }
    @Test
    @DisplayName("비밀전호가 틀렸을 때 예외처리")
    void 로그인_실패2() throws Exception {
        //given
        LoginDto loginDto = LoginDto.builder().username("test1@gmail.com").password("23@aadfend4").build();
        HttpEntity<LoginDto> dto = new HttpEntity<>(loginDto);
        ResponseEntity<String> response = restTemplate.exchange(uri("/members/login"), HttpMethod.POST, dto, String.class);
        //then
        assertThat(response.getBody().contains(HttpStatus.UNAUTHORIZED.getReasonPhrase())).isTrue();
    }


}
