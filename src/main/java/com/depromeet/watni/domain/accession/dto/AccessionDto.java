package com.depromeet.watni.domain.accession.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.depromeet.watni.domain.accession.AccessionRole;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class AccessionDto {

	@NotNull
	private List<Long> memberIdList;
	@NotNull
	private AccessionRole accessionRole;
	
	//optional
	private String code;
}
