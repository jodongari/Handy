package com.jodongari.handy.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "EXTRA_OPTION")
public class ExtraOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    Long seq;


    long extraOptionGroupSeq;

    @Column(name = "NAME", nullable = false, length = 50)
    String name;

    @Column(name = "EXTRA_FEE", nullable = false)
    Integer extraFee;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    String status;

    @Builder
    public ExtraOptionEntity(long seq, long extraOptionGroupSeq, String name, int extraFee, String status) {
        this.seq = seq;
        this.extraOptionGroupSeq = extraOptionGroupSeq;
        this.name = name;
        this.extraFee = extraFee;
        this.status = status;
    }

}
