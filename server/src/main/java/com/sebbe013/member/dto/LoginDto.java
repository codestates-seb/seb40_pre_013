package com.sebbe013.member.dto;

import lombok.Getter;

/*
로그인시 dto 클래스
 */
@Getter
public class LoginDto {
    private String username;//email
    private String password;
}
