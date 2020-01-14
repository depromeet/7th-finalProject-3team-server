package com.depromeet.watni.domain.group;

import com.depromeet.watni.domain.conference.Conference;
import com.depromeet.watni.domain.group.dto.GroupResponseDto;
import com.depromeet.watni.domain.manager.Manager;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "groups")
@Entity
@Builder
@Getter
@ToString
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private long groupId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group")
    @Builder.Default
    private List<Conference> conferences = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    @Builder.Default
    private List<Manager> managers = new ArrayList<>();
   
    //map struct
    public GroupResponseDto toResponseDto() {
    	return GroupResponseDto.builder().groupId(this.groupId).name(this.name).conferences(this.conferences).build();
    }

    // TODO createdAt, modifiedAt
}
