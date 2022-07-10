package com.jodongari.handy.domain.entity;

import com.jodongari.handy.domain.entity.status.ExtraOptionGroupStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "EXTRA_OPTION_GROUP")
public class ExtraOptionGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "extraOptionGroupEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private final List<ExtraOptionEntity> extraOptionEntities = new ArrayList<>();

    @Builder
    public ExtraOptionGroupEntity(long seq, String name, String type, int minSelectLimit,
                                  int maxSelectLimit, ExtraOptionGroupStatus status, MenuEntity menuEntity) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
        this.menuEntity = menuEntity;
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
