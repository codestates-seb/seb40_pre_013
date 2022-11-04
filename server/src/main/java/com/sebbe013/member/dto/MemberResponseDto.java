package com.sebbe013.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private String email;
    private String displayName;
    private LocalDateTime createdAt;
    private List<String> roles;
}
