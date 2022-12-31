package com.jodongari.handy.protocol.dto.model;

import com.jodongari.handy.domain.menu.ExtraOption;
import com.jodongari.handy.domain.menu.vo.ExtraOptionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtraOptionModel {

    Long seq;
    String name;
    Integer extraPrice;
    ExtraOptionStatus status;
    Long extraOptionGroupSeq;

    @Builder
    public ExtraOptionModel(Long seq, String name, Integer extraPrice, ExtraOptionStatus status, Long extraOptionGroupSeq) {
        this.seq = seq;
        this.name = name;
        this.extraPrice = extraPrice;
        this.status = status;
        this.extraOptionGroupSeq = extraOptionGroupSeq;
    }

    public void addExtraOptionGroupSeq(final long extraOptionGroupSeq) {
//        if (this.extraOptionGroupSeq == -1) {
            this.extraOptionGroupSeq = extraOptionGroupSeq;
//        }
    }

    public ExtraOption toEntity() {
        return ExtraOption.builder()
                .name(this.name)
                .extraPrice(this.extraPrice)
                .status(this.status)
                .extraOptionGroupSeq(this.extraOptionGroupSeq)
                .build();
    }
}
