package com.depromeet.watni.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "member")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    // TODO createdAt, modifiedAt
}
