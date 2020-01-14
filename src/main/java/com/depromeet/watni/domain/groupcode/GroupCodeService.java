package com.depromeet.watni.domain.groupcode;

import org.springframework.stereotype.Service;

import com.depromeet.watni.domain.group.Group;
import com.depromeet.watni.domain.group.GroupRepository;
import com.depromeet.watni.domain.group.GroupService;
import com.depromeet.watni.exception.BadRequestException;
import com.depromeet.watni.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupCodeService {
	private final GroupCodeRepository groupCodeRepository;
	private final GroupRepository groupRepository;

	public void checkRedundantGroupCode(String code) {
		if (groupCodeRepository.findOneByCode(code).isPresent()) {
			throw new BadRequestException("This code is redundant.");
		}
	}

	public GroupCode createGroupCode(Long groupId, String code) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("NOT FOUND GROUP"));
		checkRedundantGroupCode(code);
		GroupCode groupCode = GroupCode.builder().group(group).code(code).build();
		return groupCodeRepository.save(groupCode);
	}
	
	public void checkGroupCode(Long groupId, String code) {
		Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("NOT FOUND GROUP"));
		groupCodeRepository.findOneByGroupAndCode(group, code).orElseThrow(()-> new BadRequestException("WRONG CODE"));
	}
}
