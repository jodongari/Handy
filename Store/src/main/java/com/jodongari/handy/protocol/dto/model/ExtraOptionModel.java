package com.jodongari.handy.protocol.dto.model;

import com.jodongari.handy.domain.menu.ExtraOption;
import com.jodongari.handy.domain.menu.vo.ExtraOptionStatus;
import lombok.Builder;
import lombok.Value;

@Value
public class ExtraOptionModel {

    Long seq;
    String name;
    Integer extraPrice;
    ExtraOptionStatus status;

    @Builder
    public ExtraOptionModel(Long seq, String name, Integer extraPrice, ExtraOptionStatus status) {
        this.seq = seq;
        this.name = name;
        this.extraPrice = extraPrice;
        this.status = status;
    }

    public ExtraOption toEntity() {
        return ExtraOption.builder()
                .name(this.name)
                .extraPrice(this.extraPrice)
                .status(this.status)
                .build();
    }
}
