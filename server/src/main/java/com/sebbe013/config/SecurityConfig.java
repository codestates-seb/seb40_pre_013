package com.sebbe013.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*
스프링 시큐리티 적용 클래스
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    // 비밀번호 암호화 메서드
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception{
        http.csrf().disable(); //CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화

        /*
        x-frame옵션 헤더는 <frame>이나<iframe> 렌더링할 수 있는지 나타내는데 대부분 사이트들은 clickjacking을 막기 위해 사용되고
        스프링 시큐리티는 기본적으로 deny세팅이라 이것을 풀어줘야 h2가 렌더링 된다.
         */
        http.headers().frameOptions().disable();

        http.authorizeRequests() // 접근권한 확인
//                .antMatchers("/users/**").authenticated()// /users/** url 접근은 권한이 있어야함.
//                .antMatchers("/admin/**").access("has('ROLE_ADMIN')")// admin권한이 있는 유저만 접근가능함.
                .anyRequest().permitAll() // 그외 나머지는 모두 접근 가능
                .and()
                .formLogin(); // 기본적인 로그인 방식을 폼 로그인으로 함.
//                .loginPage("~~") //권한확인을 위한  로그인페이지가 나온다.// 스프링에서 만든 것은 없어진다.
//                .loginProcessingUrl("/login") // url이 호출되면 시큐리티가 대신 로그인을 진행해준다.
//                .defaultSuccessUrl("/");// 로그인이 완료될 후 이동할 url
        return http.build();
    }
}
