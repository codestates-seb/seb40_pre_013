package com.sebbe013.member.mapper;

import com.sebbe013.member.dto.MemberSignUpDto;
import com.sebbe013.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberSignUpDtoToMember( MemberSignUpDto memberSignUpDto);
}
