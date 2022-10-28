package com.sebbe013.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception{
        http.csrf().disable(); //CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화
        http.authorizeRequests()
                .antMatchers("/users/**").authenticated()
//                .antMatchers("/admin/**").access("has('ROLE_ADMIN')")
                .anyRequest().permitAll();
//                .and()
//                .formLogin()
//                .loginPage(""); //위 url 어디로 들어가도 로그인페이지가 나온다.// 스프링에서 만든 것은 없어진다.
        return http.build();
    }
}
