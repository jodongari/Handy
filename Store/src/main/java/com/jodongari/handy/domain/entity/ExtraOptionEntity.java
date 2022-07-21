package com.jodongari.handy.domain.entity;

import com.jodongari.handy.domain.entity.status.ExtraOptionStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode
@Table(name = "EXTRA_OPTION")
public class ExtraOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

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
    public ExtraOptionEntity(Long seq, String name, int extraFee, ExtraOptionStatus status) {
        this.seq = seq;
        this.name = name;
        this.extraFee = extraFee;
        this.status = status;
    }

    public void addExtraOptionGroup(ExtraOptionGroupEntity extraOptionGroupEntity) {
        this.extraOptionGroupEntity = extraOptionGroupEntity;
    }

}
