package com.jodongari.handy.domain.entity;

import com.jodongari.handy.domain.entity.status.ExtraOptionStatus;
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
    private Long seq;

    @Column(name = "EXTRA_OPTION_GROUP_SEQ")
    private Long extraOptionGroupSeq;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "EXTRA_FEE", nullable = false)
    private Integer extraFee;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ExtraOptionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "extraOptionGroupSeq")
    private ExtraOptionGroupEntity extraOptionGroupEntity;

    @Builder
    public ExtraOptionEntity(long seq, long extraOptionGroupSeq, String name,
                             int extraFee, ExtraOptionStatus status, ExtraOptionGroupEntity extraOptionGroupEntity) {
        this.seq = seq;
        this.extraOptionGroupSeq = extraOptionGroupSeq;
        this.name = name;
        this.extraFee = extraFee;
        this.status = status;
        this.extraOptionGroupEntity = extraOptionGroupEntity;
    }

}
