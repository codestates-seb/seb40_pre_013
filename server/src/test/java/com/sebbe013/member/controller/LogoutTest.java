package com.sebbe013.member.controller;

import com.sebbe013.login.filter.JwtVerificationFilter;
import com.sebbe013.login.jwt.JwtToken;
import com.sebbe013.login.jwt.SecretKey;
import com.sebbe013.member.dto.LoginDto;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import com.sebbe013.member.mapper.MemberMapper;
import com.sebbe013.member.repository.MemberRepository;
import com.sebbe013.member.service.MemberService;
import com.sebbe013.redis.RedisConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //내장 서버를 랜덤 포트로 띄운다. 테스트를 위한 서블릿 컨테이너를 띄운다.
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LogoutTest {
    @Autowired
    private RedisConfig redis;
    private final String REDIS_KEY_PREFIX = "logouttoken";
    private String baseKey = "dfkemekemkm12312rtrtrtrtrtg3123123123";
    @Autowired
    private SecretKey secretKey;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper mapper;
    @Autowired
    private JwtVerificationFilter jwtVerificationFilter;

    @LocalServerPort //로컬 서버 포트를 사용한다.
    private int port;

    private URI uri( String path ) throws URISyntaxException{ //uri를 생성한다.
        return new URI(format("http://localhost:%d%s", port, path));
    }

    private RestTemplate restTemplate = new RestTemplate(); //랜덤 포트를 사용하면 resttemplate을 사용해야한다. 서버간 통신??

    @BeforeEach
    void init(){
        memberRepository.deleteAll();
        MemberSignUpDto memberDto1 = MemberSignUpDto.builder().email("test@gmail.com").displayName("test").password("123@aadfend4").build();
        Member member = mapper.memberSignUpDtotoMember(memberDto1);
        memberService.joinMember(member);
    }

    @Test
    void 로그아웃_성공() throws Exception{
        //given
        LoginDto loginDto = LoginDto.builder().username("test@gmail.com").password("123@aadfend4").build();
        HttpEntity<LoginDto> dto = new HttpEntity<>(loginDto);//logindto 내용이 바디값으로 들어간다??
        ResponseEntity<String> response = restTemplate.exchange(uri("/members/login"), HttpMethod.POST, dto, String.class); //해당 url에 포스트로 dto값을 보내고 응답을 받는다??
        List<String> authorization = response.getHeaders().get("Authorization");

        //then
        mockMvc.perform(get("/members/logout").header("Authorization",authorization))
                .andExpect(status().isOk())
                .andExpect(content().string("로그아웃 되었습니다."));
    }

    @Test
    @DisplayName("로그아웃되지 않은 토큰은 true")
    void 로그아웃_메서드_테스트() throws Exception {
        //given
        Key key = secretKey.getSecretKey(baseKey);
        String jws = makeAccessToken(15,Calendar.MINUTE);
        //when
        Boolean result = logoutedTokenTest(jws, key);
        //then
        Assertions.assertThat(result).isTrue();
    }
    @Test
    @DisplayName("로그아웃된 토큰")
    void 로그아웃_메서드_테스트2() throws Exception {
        //given
        Key key = secretKey.getSecretKey(baseKey);
        String jws = makeAccessToken(10, Calendar.MINUTE);
        //then
        assertDoesNotThrow(() ->logoutTest(jws,key));
    }



    private void logoutTest(String jws, Key key){
        Jws<Claims> claims = jwtVerificationFilter.getClaims(jws, key);
        Date expiration = claims.getBody().getExpiration();

        if(logoutedTokenTest(jws,key)){
            redis.redisTemplate().opsForValue()
                    .set(REDIS_KEY_PREFIX + jws, "tk", expiration.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }


    private Boolean logoutedTokenTest(String jws, Key key){
        Jws<Claims> claims = jwtVerificationFilter.getClaims(jws, key);
        boolean notExpire = claims.getBody().getExpiration().after(new Date());//현재시간보다 만료일이 더 나중이면 true

        if(redis.redisTemplate().opsForValue().get(REDIS_KEY_PREFIX + jws) != null){ //만약 레디스에 토큰이 있으면 로그아웃된 유저
            return false;
        }
        return notExpire;
    }

    private String makeAccessToken(int time, int timeUnit){
        String baseKey = "dfkemekemkm12312rtrtrtrtrtg3123123123";
        Key key = secretKey.getSecretKey(baseKey);
        Member member = Member.builder().memberId(1L).displayName("test").email("test@gmail.com").displayName("test").build();
        return getAcessToken(key, member, time, timeUnit);
    }

    private String getAcessToken( Key key, Member member, int time, int timeUnit ){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", member.getEmail());
        claims.put("memberId", member.getMemberId());
        claims.put("displayName", member.getDisplayName());

        String subject = "test token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(timeUnit, time);
        Date expiration = calendar.getTime();

        String acessToken = jwtToken.createAcessToken(claims, subject, expiration, key);
        return acessToken;
    }

}