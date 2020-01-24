package com.depromeet.watni.utils;

import com.depromeet.watni.domain.member.dto.MemberRequestDto;
import com.depromeet.watni.domain.member.service.MemberService;
import com.depromeet.watni.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommandLine implements CommandLineRunner {

    @Autowired
    private Environment environment;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberService memberService;

    @Override
    public void run(String... args) throws Exception {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        if (profiles.contains("local")) {
            try {
                memberService.selectByMemberId(1L);
            } catch (NotFoundException e) {
                memberService.createMember(new MemberRequestDto("test@naver.com", passwordEncoder.encode("test"), "test"));
            }
        }
    }
}
