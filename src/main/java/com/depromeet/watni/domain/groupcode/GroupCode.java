package com.depromeet.watni.domain.groupcode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.depromeet.watni.domain.group.Group;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class GroupCode {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeId;
	@OneToOne
	@JoinColumn(name = "group_id")
	private Group group;
	private String code;
}