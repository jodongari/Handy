package com.jodongari.handy.domain.entity;

import com.jodongari.handy.domain.entity.status.ExtraOptionGroupStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "EXTRA_OPTION_GROUP")
@EqualsAndHashCode
public class ExtraOptionGroupEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuSeq")
    private MenuEntity menuEntity;

    @OneToMany(mappedBy = "extraOptionGroupEntity", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final List<ExtraOptionEntity> extraOptionEntities = new ArrayList<>();

    @Builder
    public ExtraOptionGroupEntity(Long seq, String name, String type, Integer minSelectLimit,
                                  Integer maxSelectLimit, ExtraOptionGroupStatus status) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
    }

    public void addExtraOption(ExtraOptionEntity extraOptionEntity) {
        this.getExtraOptionEntities().add(extraOptionEntity);
        extraOptionEntity.addExtraOptionGroup(this);
    }

    public void addAllExtraOption(List<ExtraOptionEntity> extraOptionEntities) {
        for(ExtraOptionEntity extraOptionEntity : extraOptionEntities) {
            this.addExtraOption(extraOptionEntity);
        }
    }

    public void addMenu(MenuEntity menuEntity) {
        this.menuEntity = menuEntity;
    }

}
