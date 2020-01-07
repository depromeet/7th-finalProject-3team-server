package com.depromeet.watni.domain.member;

import org.springframework.stereotype.Service;

import com.depromeet.watni.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {
	private MemberRepository memberRepository;
	
	public Member getMember(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(()-> new NotFoundException("존재하지 않는 memberId입니다"));
	}
	

}
