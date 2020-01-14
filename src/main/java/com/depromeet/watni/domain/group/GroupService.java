package com.depromeet.watni.domain.group;

import org.springframework.stereotype.Service;

import com.depromeet.watni.domain.accession.AccessionRepository;
import com.depromeet.watni.domain.group.dto.GroupDto;
import com.depromeet.watni.domain.groupcode.GroupCodeRepository;
import com.depromeet.watni.domain.member.MemberRepository;
import com.depromeet.watni.exception.BadRequestException;
import com.depromeet.watni.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
	private final GroupRepository groupRepository;

	public Group createGroup(GroupDto groupDto) {
		Group group = Group.builder().name(groupDto.getGroupName()).build();
		return groupRepository.save(group);
	}

	public Group getGroup(Long groupId) {
		return groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("NOT FOUND GROUP"));
	}

}
