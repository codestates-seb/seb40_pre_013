package com.sebbe013.member.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class MemberSignUpDto {

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
//    @Pattern(regexp = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$")
    // 이메일 주소 정규식표현
    private String email;
    @NotBlank(message = "display name은 공백이 아니어야 합니다.")
//    @Pattern(regexp = "^\\S+(\\s?\\S+)*$", message = "Display Name에 공백이 포함되어 있습니다.")
    private String displayName; // 띄어쓰기 허용할

    @NotBlank(message = "패스워드를 입력해 주세요.")
    // 8자 이상, 하나 이상의 문자, 하나의 이상의 숫자 및 하나의 이상의 특수 문자 정규식
   // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
    // massage = "비밀번호는 특수 문자 1개 이상, 숫자 1개 이상을 포함한 영문자 8자 이상이어야 합니다. )
    private String password;

}
