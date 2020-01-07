package com.depromeet.watni.domain.accession;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.depromeet.watni.domain.group.Group;
import com.depromeet.watni.domain.group.GroupService;
import com.depromeet.watni.domain.member.Member;
import com.depromeet.watni.domain.member.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessionService {
	private final AccessionRepository accessionRepository;
	private final GroupService groupService;
	private final MemberService memberService;

	@Transactional
	public List<Accession> accessGroupByManagers (Long groupId, List<Long> memberIdList) {
		Group group = groupService.getGroup(groupId);
		List<Accession> accesionList = new ArrayList<Accession>();
		for (Long memberId : memberIdList) {
			Member member = memberService.getMember(memberId);
			Accession accession = Accession.builder()
					.accessionRole(AccessionRole.MANAGER).accessionType(AccessionType.AUTO).accessionStatus(AccessionStatus.ACCEPT)
					.group(group).member(member)
					.build();
			accesionList.add(accession);
		}
		return accessionRepository.saveAll(accesionList);
	}

}
