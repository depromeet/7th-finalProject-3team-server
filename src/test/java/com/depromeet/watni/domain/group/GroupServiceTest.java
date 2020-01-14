package com.depromeet.watni.domain.group;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.depromeet.watni.domain.group.dto.GroupDto;
import com.depromeet.watni.domain.groupcode.GroupCode;
import com.depromeet.watni.domain.groupcode.GroupCodeService;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class GroupServiceTest {
	@Autowired
	GroupService groupService;
	@Autowired
	GroupCodeService groupCodeService;

	@Test
	public void 그룹_만들기() {
		GroupDto groupDto = GroupDto.builder().code("testcode").groupName("테스트그룹").build();
		Group group = groupService.createGroup(groupDto);
		Assert.assertNotNull(group);
	}
	@Test
	public void 그룹_코드() {
		GroupDto groupDto = GroupDto.builder().code("testcode").groupName("테스트그룹").build();
		Group group = groupService.createGroup(groupDto);
		GroupCode groupCode = groupCodeService.createGroupCode(group.getGroupId(), "testcode");
		System.out.println(groupCode);
	}
}