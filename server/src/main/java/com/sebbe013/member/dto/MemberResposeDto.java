package com.sebbe013.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MemberResposeDto {
    private String email;
    private String displayName;
    private LocalDateTime createdAt;
    private List<String> roles;
}
