package com.jodongari.handy.protocol.model.menu;

import com.jodongari.handy.domain.entity.ExtraOptionGroupEntity;
import com.jodongari.handy.domain.entity.status.ExtraOptionGroupStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExtraOptionGroup {

    private Long seq;
    private String name;
    private String type;
    private Integer minSelectLimit;
    private Integer maxSelectLimit;
    private ExtraOptionGroupStatus status;
    private List<ExtraOption> extraOptions;

    @Builder
    public ExtraOptionGroup(Long seq, String name, String type, Integer minSelectLimit, Integer maxSelectLimit,
                            ExtraOptionGroupStatus status, List<ExtraOption> extraOptions) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
        this.extraOptions = extraOptions;
    }

    public ExtraOptionGroupEntity dtoToExtraOptionGroupEntity() {
        return ExtraOptionGroupEntity.builder()
                .name(this.name)
                .type(this.type)
                .minSelectLimit(this.minSelectLimit)
                .maxSelectLimit(this.maxSelectLimit)
                .status(this.status)
                .build();
    }
}
