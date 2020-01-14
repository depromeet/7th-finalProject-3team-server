package com.depromeet.watni.domain.accession;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.depromeet.watni.domain.accession.dto.AccessionResponseDto;
import com.depromeet.watni.domain.conference.Conference;
import com.depromeet.watni.domain.group.Group;
import com.depromeet.watni.domain.group.Group.GroupBuilder;
import com.depromeet.watni.domain.member.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "accession")
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Accession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accession_id")
	private long id;

	@Column(name = "accession_type")
	@Enumerated(value = EnumType.STRING)
	private AccessionType accessionType;

	@Column(name = "accession_status")
	@Enumerated(value = EnumType.STRING)
	private AccessionStatus accessionStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@Column(name = "accession_role")
	@Enumerated(value = EnumType.STRING)
	private AccessionRole accessionRole;

	public AccessionResponseDto toResponseDto() {
		return AccessionResponseDto.builder().id(id).accessionType(accessionType).accessionStatus(accessionStatus)
				.member(member).group(group).accessionRole(accessionRole).build();
	}

	// TODO createdAt, modifiedAt
}
