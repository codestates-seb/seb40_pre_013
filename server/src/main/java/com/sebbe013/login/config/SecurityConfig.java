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

//    @Bean
//    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception{
//        http.csrf().disable(); //CSRF(Cross-Site Request Forgery) 공격에 대한 Spring Security에 대한 설정을 비활성화
//
//
//        http.headers().frameOptions().disable();  /*
//        x-frame옵션 헤더는 <frame>이나<iframe> 렌더링할 수 있는지 나타내는데 대부분 사이트들은 clickjacking을 막기 위해 사용되고
//        스프링 시큐리티는 기본적으로 deny세팅이라 이것을 풀어줘야 h2가 렌더링 된다.
//         */
//
//        http.cors(withDefaults())/* http basic : 헤더에 Anthorization이라는 키값에 id와 pwd를 넣어서 요청을 보내면 세션, 쿠키를 사용하지 않아도 계속 인증을 할 수 있다.
//                                        id와 pwd는 암호화가 안되기 때문에 노출가능성이 있어서 https서버를 써야한다.
//                                        Anthorization에 토큰을 넣는 방식이 bearer방식, 토큰을 만들때  jwt를 사ㅠ
//                                        */
//                .formLogin().disable()
//                .httpBasic().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .apply(new CustomFilterConfigurer(jwtToken, secretKey, jwtVerification))
//                .and()
//                .authorizeRequests() // 접근권한 확인
//
//                // url api api
//                .antMatchers(HttpMethod.POST,"questions").hasRole("USER")
//                .antMatchers(HttpMethod.POST,"/*/answers/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH,"members/answers/**").hasRole("USER")
//                .antMatchers(HttpMethod.PATCH,"members/questions/**").hasRole("USER")
////                .antMatchers("/admin/**").access("has('ROLE_ADMIN')")// admin권한이 있는 유저만 접근가능함.
//                .anyRequest().permitAll(); // 그외 나머지는 모두 접근 가능
//
//
//        return http.build();
//    }

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
                        .antMatchers(HttpMethod.POST, "/*/members").permitAll()         // (1) 추가
                        .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER")  // (2) 추가
                        .antMatchers(HttpMethod.GET, "/members").hasRole("USER")     // (3) 추가
                        .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")  // (4) 추가
                        .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER")  // (5) 추가
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
