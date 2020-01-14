package com.depromeet.watni.domain.accession.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.depromeet.watni.domain.accession.AccessionRole;
import com.depromeet.watni.domain.accession.AccessionStatus;
import com.depromeet.watni.domain.accession.AccessionType;
import com.depromeet.watni.domain.group.Group;
import com.depromeet.watni.domain.member.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessionResponseDto {
	private long id;

	private AccessionType accessionType;

	private AccessionStatus accessionStatus;

	private Member member;

	private Group group;

	private AccessionRole accessionRole;
}
