package com.sebbe013.member.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberSignUpDto {

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "display name은 공백이 아니어야 합니다.")
    private String displayName; // 띄어쓰기 허용할

    @NotBlank(message = "패스워드를 입력해 주세요.")
    // 8자 이상, 하나 이상의 문자, 하나의 이상의 숫자 및 하나의 이상의 특수 문자 정규식
   // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;

    @Builder
    public MemberSignUpDto( String email, String displayName, String password ){
        this.email = email;
        this.displayName = displayName;
        this.password = password;
    }
}
