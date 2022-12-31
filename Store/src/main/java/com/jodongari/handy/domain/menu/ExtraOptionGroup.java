package com.jodongari.handy.domain.menu;

import com.jodongari.handy.domain.menu.vo.ExtraOptionGroupStatus;
import com.jodongari.handy.protocol.dto.model.ExtraOptionGroupModel;
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
@Table(name = "EXTRA_OPTION_GROUP")
@EqualsAndHashCode
public class ExtraOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "NAME", nullable = false, length = 40)
    private String name;

    @Column(name = "TYPE", nullable = false, length = 1)
    private String type;

    @Column(name = "MIN_SELECT_LIMIT")
    private Integer minSelectLimit;

    @Column(name = "MAX_SELECT_LIMIT")
    private Integer maxSelectLimit;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ExtraOptionGroupStatus status;

    @Column(name = "MENU_SEQ", nullable = false)
    private Long menuSeq;

    private static final ExtraOptionGroupStatus EXTRA_OPTION_GROUP_CREATED = ExtraOptionGroupStatus.OPEN;

    @Builder
    public ExtraOptionGroup(Long seq, String name, String type, Integer minSelectLimit,
                            Integer maxSelectLimit, ExtraOptionGroupStatus status, Long menuSeq) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
        this.menuSeq = menuSeq;
    }

    public static ExtraOptionGroup create(Long menuSeq, ExtraOptionGroupModel extraOptionGroupModel) {

        ExtraOptionGroup extraOptionGroup = ExtraOptionGroup.builder()
                .name(extraOptionGroupModel.getName())
                .type(extraOptionGroupModel.getType())
                .minSelectLimit(extraOptionGroupModel.getMinSelectLimit())
                .maxSelectLimit(extraOptionGroupModel.getMaxSelectLimit())
                .status(EXTRA_OPTION_GROUP_CREATED)
                .menuSeq(menuSeq)
                .build();

        return extraOptionGroup;
    }

    public static ExtraOptionGroup merge(ExtraOptionGroupModel extraOptionGroupModel) {

        ExtraOptionGroup extraOptionGroup = ExtraOptionGroup.builder()
                .name(extraOptionGroupModel.getName())
                .type(extraOptionGroupModel.getType())
                .minSelectLimit(extraOptionGroupModel.getMinSelectLimit())
                .maxSelectLimit(extraOptionGroupModel.getMaxSelectLimit())
                .status(extraOptionGroupModel.getStatus())
                .menuSeq(extraOptionGroupModel.getMenuSeq())
                .build();

        return extraOptionGroup;
    }

    public ExtraOptionGroupModel toModel() {
        return ExtraOptionGroupModel.builder()
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
