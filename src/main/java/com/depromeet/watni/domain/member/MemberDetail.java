package com.depromeet.watni.domain.member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberDetail implements UserDetails {

    private long memberId;
    private String email;
    private String name;
    private String password;
    // TODO append filed;

    public MemberDetail(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.password = member.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getMemberId() {
        return this.memberId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }
}
