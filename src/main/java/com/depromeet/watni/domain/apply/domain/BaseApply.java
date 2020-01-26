package com.depromeet.watni.domain.apply.domain;

import com.depromeet.watni.base.BaseEntity;
import com.depromeet.watni.domain.apply.constant.ApplyType;
import com.depromeet.watni.domain.group.domain.Group;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "apply")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "apply_type",
        discriminatorType = DiscriminatorType.STRING
)
@Entity
@Getter
@Setter
public abstract class BaseApply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private long applyId;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
