package com.sebbe013.login.config;

import com.sebbe013.login.filter.Expiration;
import com.sebbe013.login.jwt.JwtToken;
import com.sebbe013.login.jwt.SecretKey;
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

    @Bean//패스워드 암호화할 메서드
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception{
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer(jwtToken, secretKey, expiration))
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/members").permitAll()         // (1) 추가
                        .antMatchers(HttpMethod.PATCH, "/members/**").hasRole("USER")  // (2) 추가
                        .antMatchers(HttpMethod.GET, "/members").hasRole("USER")     // (3) 추가
                        .antMatchers(HttpMethod.GET, "/members/**").hasAnyRole("USER", "ADMIN")  // (4) 추가
                        .antMatchers(HttpMethod.DELETE, "/members/**").hasRole("USER")  // (5) 추가
                        .antMatchers(HttpMethod.POST, "/answers").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/answers/**").hasRole("USER")
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
