package com.depromeet.watni.supports;

import com.depromeet.watni.domain.group.Group;
import com.depromeet.watni.domain.group.GroupService;
import com.depromeet.watni.domain.group.dto.GroupDto;
import com.depromeet.watni.domain.member.Member;
import com.depromeet.watni.domain.member.MemberRepository;
import com.depromeet.watni.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class SampleData {
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final GroupService groupService;
	
	public Member createMember() {
		Member member = Member.builder().name("홍길동").build();
		return memberRepository.save(member);
	}
	public Group createGroup() {
		GroupDto groupDto= GroupDto.builder().code("test").groupName("테스트그룹").build();
		return groupService.createGroup(groupDto);
	}
}
