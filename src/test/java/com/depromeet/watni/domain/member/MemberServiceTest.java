package com.depromeet.watni.domain.member;

import com.depromeet.watni.domain.member.dto.MemberRequestDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 회원_가입() {
        MemberRequestDto memberRequestDto = new MemberRequestDto("testuser@naver.com", "awerawf", "testUser");
        Member member = memberService.createMember(memberRequestDto);
        Assert.assertNotNull(member);
        Assert.assertEquals(member.getEmail(), memberRequestDto.getEmail());
        Assert.assertTrue(memberRequestDto.getPassword().equals(member.getPassword()));
        Assert.assertEquals(member.getName(), memberRequestDto.getName());
        Assert.assertTrue(member.getMemberId() > 0);
    }
}