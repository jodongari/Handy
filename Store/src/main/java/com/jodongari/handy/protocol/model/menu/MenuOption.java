package com.jodongari.handy.protocol.model.menu;

import com.jodongari.handy.domain.entity.MenuOptionEntity;
import com.jodongari.handy.domain.entity.status.MenuOptionStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOption {

    private Long seq;
    private String name;
    private Integer price;
    private MenuOptionStatus status;

    @Builder
    public MenuOption(Long seq, String name, Integer price, MenuOptionStatus status) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public MenuOptionEntity dtoToMenuOptionEntity() {
        return MenuOptionEntity.builder()
                .name(this.name)
                .price(this.price)
                .status(this.status)
                .build();
    }
}
