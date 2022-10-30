package com.sebbe013.member.mapper;

import com.sebbe013.member.entity.Member;
import com.sebbe013.stackoverflowclone.member.dto.MemberSignUpDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberSignUpDtoToMember( MemberSignUpDto memberSignUpDto);
}
