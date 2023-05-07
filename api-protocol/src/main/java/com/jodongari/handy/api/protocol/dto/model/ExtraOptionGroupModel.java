package com.jodongari.handy.api.protocol.dto.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExtraOptionGroupModel {

    private Long seq;
    private String name;
    private String type;
    private Integer minSelectLimit;
    private Integer maxSelectLimit;
    private String status; // TODO 확인 필요
    private Long menuSeq;
    private List<ExtraOptionModel> extraOptionModels;

    public void addMenuSeq(final long menuSeq) {
//        if (this.menuSeq == -1) {
            this.menuSeq = menuSeq;
//        }
    }

    @Builder
    public ExtraOptionGroupModel(Long seq, String name, String type, Integer minSelectLimit, Integer maxSelectLimit, String status, Long menuSeq, List<ExtraOptionModel> extraOptionModels) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
        this.menuSeq = menuSeq;
        this.extraOptionModels = extraOptionModels;
    }

}
