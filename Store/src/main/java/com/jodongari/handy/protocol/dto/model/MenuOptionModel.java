package com.jodongari.handy.protocol.dto.model;

import com.jodongari.handy.domain.menu.MenuOption;
import com.jodongari.handy.domain.menu.vo.MenuOptionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuOptionModel {

    Long seq;
    String name;
    Integer price;
    MenuOptionStatus status; // TODO: 확인
    Long menuSeq;

    public void addMenuSeq(final long menuSeq) {
        if (this.menuSeq == -1) {
            this.menuSeq = menuSeq;
        }
    }

    @Builder
    public MenuOptionModel(Long seq, String name, Integer price, MenuOptionStatus status, Long menuSeq) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
        this.menuSeq = menuSeq;
    }

    public MenuOption toEntity() {
        return MenuOption.builder()
                .seq(this.seq)
                .name(this.name)
                .price(this.price)
                .status(this.status)
                .menuSeq(this.menuSeq)
                .build();
    }
}
