package com.jodongari.handy.entity;

import com.jodongari.handy.entity.status.ExtraOptionGroupStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "EXTRA_OPTION_GROUP")
public class ExtraOptionGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;

    @Column(name = "MENU_SEQ")
    long menuSeq;

    @Column(name = "NAME", nullable = false, length = 40)
    String name;

    @Column(name = "TYPE", nullable = false, length = 1)
    String type;

    @Column(name = "MIN_SELECT_LIMIT")
    Integer minSelectLimit;

    @Column(name = "MAX_SELECT_LIMIT")
    Integer maxSelectLimit;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    ExtraOptionGroupStatus status;

    @Builder
    public ExtraOptionGroupEntity(long seq, long menuSeq, String name, String type, int minSelectLimit, int maxSelectLimit, ExtraOptionGroupStatus status) {
        this.seq = seq;
        this.menuSeq = menuSeq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
    }
}
