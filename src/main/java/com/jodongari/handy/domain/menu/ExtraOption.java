package com.jodongari.handy.domain.menu;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExtraOption {

    private Long seq;
    private String name;
    private Integer extraFee;
    private String status;

    @Builder
    public ExtraOption(Long seq, String name, Integer extraFee, String status) {
        this.seq = seq;
        this.name = name;
        this.extraFee = extraFee;
        this.status = status;
    }
}
