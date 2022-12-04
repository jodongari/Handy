package com.jodongari.handy.domain.menu;

import com.jodongari.handy.domain.menu.vo.ExtraOptionStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EqualsAndHashCode
@Table(name = "EXTRA_OPTION")
public class ExtraOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "EXTRA_PRICE", nullable = false)
    private Integer extraPrice;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ExtraOptionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "extraOptionGroupSeq")
    private ExtraOptionGroup extraOptionGroup;

    @Builder
    public ExtraOption(Long seq, String name, int extraPrice, ExtraOptionStatus status) {
        this.seq = seq;
        this.name = name;
        this.extraPrice = extraPrice;
        this.status = status;
    }

    public void addExtraOptionGroup(ExtraOptionGroup extraOptionGroup) {
        this.extraOptionGroup = extraOptionGroup;
    }
}
