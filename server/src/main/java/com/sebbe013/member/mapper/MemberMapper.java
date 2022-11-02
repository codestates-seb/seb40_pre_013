package com.sebbe013.member.mapper;

import com.sebbe013.member.dto.MemberResposeDto;
import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberSignUpDtotoMember( MemberSignUpDto memberSignUpDto );
    default MemberResposeDto memberToResponse(Member member) {

        MemberResposeDto memberResposeDto = new MemberResposeDto();

        memberResposeDto.setEmail(member.getEmail());
        memberResposeDto.setDisplayName(member.getDisplayName());
        memberResposeDto.setCreatedAt(member.getCreatedAt());
        memberResposeDto.setRoles(member.getRoles());

        return memberResposeDto;
    }
}
