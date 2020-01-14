package com.depromeet.watni.domain.groupcode;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupCodeController {
	private final GroupCodeService groupCodeService;
	
	@PostMapping("/api/groups/{groupId}/code")
	public GroupCode registerGroupCode (@PathVariable Long groupId,@RequestBody String code) {
		return groupCodeService.createGroupCode(groupId, code);
	}
}
