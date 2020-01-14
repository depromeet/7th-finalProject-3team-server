package com.depromeet.watni.domain.groupcode;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.depromeet.watni.domain.group.Group;

public interface GroupCodeRepository extends JpaRepository<GroupCode, Long> {
	Optional<GroupCode> findOneByCode(String code);
	Optional<GroupCode> findOneByGroupAndCode(Group group,String code);
}
