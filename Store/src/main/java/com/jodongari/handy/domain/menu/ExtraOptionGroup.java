package com.jodongari.handy.domain.menu;

import com.jodongari.handy.infrastructure.entity.status.ExtraOptionGroupStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuSeq")
    private Menu menuEntity;

    @OneToMany(mappedBy = "extraOptionGroupEntity", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final List<ExtraOption> extraOptionEntities = new ArrayList<>();

    @Builder
    public ExtraOptionGroup(Long seq, String name, String type, Integer minSelectLimit,
                            Integer maxSelectLimit, ExtraOptionGroupStatus status) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
    }

    public void addExtraOption(ExtraOption extraOptionEntity) {
        this.getExtraOptionEntities().add(extraOptionEntity);
        extraOptionEntity.addExtraOptionGroup(this);
    }

    public void addAllExtraOption(List<ExtraOption> extraOptionEntities) {
        for(ExtraOption extraOptionEntity : extraOptionEntities) {
            this.addExtraOption(extraOptionEntity);
        }
    }

    public void addMenu(Menu menuEntity) {
        this.menuEntity = menuEntity;
    }

}
