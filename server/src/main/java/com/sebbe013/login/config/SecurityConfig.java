package com.sebbe013.login.config;

import com.sebbe013.login.handler.MemberAccessDeniedHandler;
import com.sebbe013.login.handler.MemberAuthenticationEntryPoint;
import com.sebbe013.login.jwt.Expiration;
import com.sebbe013.login.jwt.JwtToken;
import com.sebbe013.login.jwt.SecretKey;
import com.sebbe013.redis.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


/*
스프링 시큐리티 적용 클래스
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtToken jwtToken;
    private final SecretKey secretKey;
    private final Expiration expiration;
    private final RedisConfig redisConfig;

    @Bean//패스워드 암호화할 메서드
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception{
        http
                .headers().frameOptions().sameOrigin()//CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화
                .and()
                .csrf().disable()/*
                                     x-frame옵션 헤더는 <frame>이나<iframe> 렌더링할 수 있는지 나타내는데 대부분 사이트들은 clickjacking을 막기 위해 사용되고
                                     스프링 시큐리티는 기본적으로 deny세팅이라 이것을 풀어줘야 h2가 렌더링 된다.
                                  */
                .cors(withDefaults())//cors필터 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용안함
                .and()
                .formLogin().disable() //폼 로그인 형식 사용 안함.
                .httpBasic().disable()//기본 http방식 헤더에 id, 비밀번호 담는 방식?? 사용안함
                .exceptionHandling()
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .and()
                .apply(new CustomFilterConfigurer(jwtToken, secretKey, expiration, redisConfig))//커스텀 필터 적용
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/members").permitAll()         //회원가입
                        .antMatchers(HttpMethod.POST, "/answers").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/answers/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/answers/**").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "/questions").hasRole("USER") //질문 포스트
                        .antMatchers(HttpMethod.PATCH, "/questions/**").hasRole("USER")//질문 수정
                        .antMatchers(HttpMethod.DELETE, "/questions/**").hasRole("USER")//질문 삭제
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
