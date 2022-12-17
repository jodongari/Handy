package com.jodongari.handy.protocol.dto.model;

import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.vo.ExtraOptionGroupStatus;
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
    private ExtraOptionGroupStatus status; // TODO 확인 필요
    private Long menuSeq;
    private List<ExtraOptionModel> extraOptionModels;

    public void addMenuSeq(final long menuSeq) {
        if (this.menuSeq == -1) {
            this.menuSeq = menuSeq;
        }
    }

    @Builder
    public ExtraOptionGroupModel(Long seq, String name, String type, Integer minSelectLimit, Integer maxSelectLimit, ExtraOptionGroupStatus status, Long menuSeq, List<ExtraOptionModel> extraOptionModels) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
        this.menuSeq = menuSeq;
        this.extraOptionModels = extraOptionModels;
    }

    public ExtraOptionGroup toEntity() {
        return ExtraOptionGroup.builder()
                .seq(this.seq)
                .name(this.name)
                .type(this.type)
                .minSelectLimit(this.minSelectLimit)
                .maxSelectLimit(this.maxSelectLimit)
                .status(this.status)
                .menuSeq(this.menuSeq)
                .build();
    }
}
