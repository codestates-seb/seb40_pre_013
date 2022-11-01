package com.sebbe013.login.auth;

import com.sebbe013.member.entity.MemberRoles;
import org.springframework.stereotype.Component;

import java.util.List;

//권한 부여 클래스

@Component
public class AuthUtils {
    private final List<String> USER_ROLE_STRING = List.of(MemberRoles.USER.name());
    public List<String> createRole( String email ){
      return USER_ROLE_STRING;
    }
}
