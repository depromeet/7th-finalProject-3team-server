package com.depromeet.watni.domain.member;

import com.depromeet.watni.domain.member.dto.MemberRequestDto;
import com.depromeet.watni.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("member not found"));
        return new MemberDetail(member);
    }

    public Member createMember(MemberRequestDto memberRequestDto) {
        Member member = Member
                .builder()
                .email(memberRequestDto.getEmail())
                .name(memberRequestDto.getName())
                .password(memberRequestDto.getPassword())
                .build();
        member = memberRepository.save(member);
        return member;
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(()-> new NotFoundException("NOT FOUND MEMBER"));
    }

}
