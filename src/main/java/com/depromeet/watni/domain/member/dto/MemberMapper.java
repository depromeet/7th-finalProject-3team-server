package com.depromeet.watni.domain.member.dto;

import com.depromeet.watni.domain.member.MemberDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MemberMapper {

    @Mapping(target = "userId", source = "memberId")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "name")
    @Mapping(target = "group", source = "", ignore = true)
    MemberResponse convert(MemberDetail memberDetail);
}
