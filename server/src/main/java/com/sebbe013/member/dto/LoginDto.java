package com.sebbe013.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
로그인시 dto 클래스
 */
@Getter
@NoArgsConstructor//이게 있어야 로그인 테스트가 성공. messageConverter가 json을 object로 변화시켜주기 위해서 필요.
public class LoginDto {
    private String username;//email
    private String password;
    @Builder //테스트를 위한 빌더 패턴
    public LoginDto( String username, String password ){
        this.username = username;
        this.password = password;
    }
}
