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
//    @Pattern(regexp = "^[A-Za-z0-9_]+[A-Za-z0-9]@[A-Za-z0-9]+[A-Za-z0-9][.][A-Za-z]{1,3}$",
//             message = "@포함해서 영문 숫자로 작성")

    private String email;
    @NotBlank(message = "display name은 공백이 아니어야 합니다.")
    private String displayName;

    @NotBlank(message = "패스워드를 입력해 주세요.")
//    @Pattern(regexp = "^(?=.[a-zA-Z])(?=.[!@#$%^+=-])(?=.[0-9]).{8,25}$",
//                message = "비밀번호는 숫자+영문자+특수문자 조합으로 8자리 이상 입력")
    private String password;

}
