package com.sebbe013.member.dto;

import lombok.Builder;
import lombok.Getter;

/*
로그인시 리스폰스 바디로 멤버 아이디 값 넘겨줄 때 dto
안 쓸 것 같음
 */
@Builder
@Getter
public class LoginResponseDto {
    private Long id;
}
