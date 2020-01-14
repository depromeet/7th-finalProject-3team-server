package com.depromeet.watni.domain.group.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import com.depromeet.watni.domain.conference.Conference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class GroupResponseDto {
	private long groupId;

	private String name;

	private List<Conference> conferences = new ArrayList<>();

	private String code;

}
