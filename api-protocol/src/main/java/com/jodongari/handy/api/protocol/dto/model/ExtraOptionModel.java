package com.jodongari.handy.api.protocol.dto.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtraOptionModel {

    Long seq;
    String name;
    Integer extraPrice;
    String status;
    Long extraOptionGroupSeq;

    @Builder
    public ExtraOptionModel(Long seq, String name, Integer extraPrice, String status, Long extraOptionGroupSeq) {
        this.seq = seq;
        this.name = name;
        this.extraPrice = extraPrice;
        this.status = status;
        this.extraOptionGroupSeq = extraOptionGroupSeq;
    }

    public void addExtraOptionGroupSeq(final long extraOptionGroupSeq) {
        this.extraOptionGroupSeq = extraOptionGroupSeq;
    }
}
