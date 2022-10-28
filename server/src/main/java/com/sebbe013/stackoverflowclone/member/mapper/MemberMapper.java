package com.sebbe013.stackoverflowclone.member.mapper;

import com.sebbe013.stackoverflowclone.member.dto.MemberSignUpDto;
import com.sebbe013.stackoverflowclone.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberSignUpDtoToMember( MemberSignUpDto memberSignUpDto);
}
