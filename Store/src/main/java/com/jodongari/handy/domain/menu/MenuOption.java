package com.jodongari.handy.domain.menu;

import com.jodongari.handy.domain.menu.vo.MenuOptionStatus;
import com.jodongari.handy.protocol.dto.model.MenuOptionModel;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MENU_OPTION")
@EqualsAndHashCode
public class MenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MenuOptionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuSeq")
    private Menu menu;

    @Builder
    public MenuOption(Long seq, String name, Integer price, MenuOptionStatus status) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
        this.menu = menu;
    }

    public void addMenuEntity(Menu menuEntity) {
        this.menu = menuEntity;
    }

    public MenuOptionModel toModel() {
        return MenuOptionModel.builder()
                .seq(this.seq)
                .name(this.name)
                .price(this.price)
                .status(this.status)
                .build();
    }
}
