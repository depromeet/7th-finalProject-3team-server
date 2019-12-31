package com.depromeet.watni.domain.attendance;

import com.depromeet.watni.domain.conference.Conference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "attendance")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "attendance_type")
@Entity
public abstract class BaseAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private long id;

    @Column(name = "member_id")
    private long memberId;

    @Column(name = "attenance_type")
    @Enumerated(value = EnumType.STRING)
    private AttendanceType attendanceType;

    @Column(name = "attendance_at")
    private LocalDateTime attendanceAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id")
    private Conference conference;

    // TODO 논의 거리 출석체크를 시간 기반으로 진행할지 on/off로 기록할지
    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;
}
