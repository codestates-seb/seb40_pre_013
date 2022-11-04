package com.sebbe013.member.mapper;

import com.sebbe013.member.dto.MemberResponseDto;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberSignUpDtotoMember( MemberSignUpDto memberSignUpDto );
    default MemberResponseDto memberToResponse( Member member) {

        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .email(member.getEmail())
                .roles(member.getRoles())
                .createdAt(member.getCreatedAt())
                .displayName(member.getDisplayName())
                .build();

        return memberResponseDto;
    }
}
