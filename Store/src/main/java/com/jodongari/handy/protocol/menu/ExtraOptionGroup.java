package com.jodongari.handy.protocol.menu;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ExtraOptionGroup {

    private Long seq;
    private String name;
    private String type;
    private Integer minSelectLimit;
    private Integer maxSelectLimit;
    private String status;
    private List<ExtraOption> extraOptions;

    @Builder
    public ExtraOptionGroup(Long seq, String name, String type, Integer minSelectLimit, Integer maxSelectLimit, String status, List<ExtraOption> extraOptions) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
        this.extraOptions = extraOptions;
    }
}
