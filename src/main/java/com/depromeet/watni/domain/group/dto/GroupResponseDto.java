package com.depromeet.watni.domain.group.dto;

import com.depromeet.watni.domain.conference.Conference;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class GroupResponseDto {
	private long groupId;

	private String name;

	@Builder.Default
	private List<Conference> conferences = new ArrayList<>();

	private String code;

}
