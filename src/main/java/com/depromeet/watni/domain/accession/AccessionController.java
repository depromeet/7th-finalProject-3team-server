package com.depromeet.watni.domain.accession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.watni.domain.accession.dto.AccessionDto;
import com.depromeet.watni.domain.accession.dto.AccessionResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccessionController {
	private final AccessionService accessionService;

	@PostMapping("/api/group/{groupId}/accession")
	public List<AccessionResponseDto> accessGroup(@PathVariable Long groupId, @RequestBody AccessionDto accessionDto) {
		List<Accession> accessions= accessionService.accessGroupByCode(groupId, accessionDto.getMemberIdList(),accessionDto.getAccessionRole());
		// group exception
		// group -> code exception
		// code / auto -> create Accession
		// return GROUP, SESSION
		List<AccessionResponseDto> result = new ArrayList<AccessionResponseDto>();
		for (Accession accession : accessions) {
			result.add(accession.toResponseDto());
		}
		return result;
	}
}
