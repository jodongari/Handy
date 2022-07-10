package com.jodongari.handy.protocol.model.menu;

import com.jodongari.handy.domain.entity.ExtraOptionEntity;
import com.jodongari.handy.domain.entity.status.ExtraOptionStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraOption {

    private Long seq;
    private String name;
    private Integer extraFee;
    private ExtraOptionStatus status;

    @Builder
    public ExtraOption(Long seq, String name, Integer extraFee, ExtraOptionStatus status) {
        this.seq = seq;
        this.name = name;
        this.extraFee = extraFee;
        this.status = status;
    }

    public ExtraOptionEntity dtoToExtraOptionEntity() {
        return ExtraOptionEntity.builder()
                .name(this.name)
                .extraFee(this.extraFee)
                .status(this.status)
                .build();
    }
}
