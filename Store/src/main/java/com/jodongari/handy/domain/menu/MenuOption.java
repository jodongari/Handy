package com.jodongari.handy.domain.menu;

import com.jodongari.handy.domain.menu.vo.MenuOptionStatus;
import com.jodongari.handy.protocol.dto.model.MenuOptionModel;
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

    @Column(name = "MENU_SEQ", nullable = false)
    private Long menuSeq;

    private static MenuOptionStatus MENU_OPTION_CREATED = MenuOptionStatus.OPEN;

    @Builder
    public MenuOption(Long seq, String name, Integer price, MenuOptionStatus status, Long menuSeq) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
        this.menuSeq = menuSeq;
    }

    public static MenuOption create(MenuOptionModel menuOptionModel) {

        MenuOption menuOption = MenuOption.builder()
                .name(menuOptionModel.getName())
                .price(menuOptionModel.getPrice())
                .status(MENU_OPTION_CREATED)
                .menuSeq(menuOptionModel.getMenuSeq())
                .build();

        return menuOption;
    }

    public MenuOptionModel toModel() {
        return MenuOptionModel.builder()
                .seq(this.seq)
                .name(this.name)
                .price(this.price)
                .status(this.status)
                .menuSeq(this.menuSeq)
                .build();
    }
}
