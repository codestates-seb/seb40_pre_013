package com.sebbe013.member.mapper;

import com.sebbe013.member.dto.MemberResponseDto;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MemberMapper {

    default Member memberSignUpDtotoMember( MemberSignUpDto memberSignUpDto ){

        return Member.builder().password(memberSignUpDto.getPassword())
                .email(memberSignUpDto.getEmail())
                .displayName(memberSignUpDto.getDisplayName())
                .build();
    }
    default MemberResponseDto memberToResponse( Member member) {

        return MemberResponseDto.builder()
                .email(member.getEmail())
                .roles(member.getRoles())
                .createdAt(member.getCreatedAt())
                .displayName(member.getDisplayName())
                .build();
    }
}
