package com.jodongari.handy.domain.menu;

import com.jodongari.handy.domain.menu.vo.ExtraOptionGroupStatus;
import com.jodongari.handy.protocol.dto.model.ExtraOptionGroupModel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    private Menu menu;

    @OneToMany(mappedBy = "extraOptionGroup", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ExtraOption> extraOptions = new ArrayList<>();

    @Builder
    public ExtraOptionGroup(Long seq, String name, String type, Integer minSelectLimit,
                            Integer maxSelectLimit, ExtraOptionGroupStatus status, List<ExtraOption> extraOptions) {
        this.seq = seq;
        this.name = name;
        this.type = type;
        this.minSelectLimit = minSelectLimit;
        this.maxSelectLimit = maxSelectLimit;
        this.status = status;
        this.extraOptions= extraOptions;
    }

    public void addExtraOption(ExtraOption extraOption) {
        this.getExtraOptions().add(extraOption);
        extraOption.addExtraOptionGroup(this);
    }

    public void addAllExtraOption(List<ExtraOption> extraOptions) {
        for(ExtraOption extraOption : extraOptions) {
            this.addExtraOption(extraOption);
        }
    }

    public void addMenu(Menu menuEntity) {
        this.menu = menuEntity;
    }

    public ExtraOptionGroupModel toModel() {
        return ExtraOptionGroupModel.builder()
                .seq(this.seq)
                .name(this.name)
                .type(this.type)
                .minSelectLimit(this.minSelectLimit)
                .maxSelectLimit(this.maxSelectLimit)
                .status(this.status)
                .extraOptionModels(this.extraOptions.stream().map(ExtraOption::toModel).collect(Collectors.toList()))
                .build();
    }
}
