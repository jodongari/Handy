package com.jodongari.handy.domain.menu;

import com.jodongari.handy.api.protocol.dto.model.ExtraOptionModel;
import com.jodongari.handy.domain.menu.vo.ExtraOptionStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode
@Table(name = "EXTRA_OPTION")
public class ExtraOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "EXTRA_PRICE", nullable = false)
    private Integer extraPrice;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ExtraOptionStatus status;

    @Column(name = "EXTRA_OPTION_GROUP_SEQ", nullable = false)
    private Long extraOptionGroupSeq;

    private static ExtraOptionStatus EXTRA_OPTION_CREATED = ExtraOptionStatus.OPEN;

    @Builder
    public ExtraOption(Long seq, String name, Integer extraPrice, ExtraOptionStatus status, Long extraOptionGroupSeq) {
        this.seq = seq;
        this.name = name;
        this.extraPrice = extraPrice;
        this.status = status;
        this.extraOptionGroupSeq = extraOptionGroupSeq;
    }

    public static ExtraOption create(Long extraOptionGroupSeq, ExtraOptionModel extraOptionModel) {

        ExtraOption extraOption = ExtraOption.builder()
                .name(extraOptionModel.getName())
                .extraPrice(extraOptionModel.getExtraPrice())
                .status(EXTRA_OPTION_CREATED)
                .extraOptionGroupSeq(extraOptionGroupSeq)
                .build();

        return extraOption;
    }

    public static ExtraOption merge(ExtraOptionModel extraOptionModel) {

        ExtraOption extraOption = ExtraOption.builder()
                .seq(extraOptionModel.getSeq())
                .name(extraOptionModel.getName())
                .extraPrice(extraOptionModel.getExtraPrice())
                .status(ExtraOptionStatus.valueOf(extraOptionModel.getStatus()))
                .extraOptionGroupSeq(extraOptionModel.getExtraOptionGroupSeq())
                .build();

        return extraOption;
    }

}
