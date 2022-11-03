package com.sebbe013.member.login.auth;

import com.sebbe013.member.entity.MemberRoles;
import org.springframework.stereotype.Component;

import java.util.List;

/*
역할 부여 클래스
 */

@Component
public class AuthUtils {
    private final List<String> USER_ROLE = List.of(MemberRoles.USER.name());
    public List<String> createRole( String email ){ //회원가입시 디폴트 값으로 user 역할 부여
      return USER_ROLE;
    }
}
