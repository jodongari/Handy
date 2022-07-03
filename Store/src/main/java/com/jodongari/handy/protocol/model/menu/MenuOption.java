package com.jodongari.handy.protocol.model.menu;

import lombok.Builder;
import lombok.Getter;
@Getter
public class MenuOption {

    private Long seq;
    private String name;
    private Integer price;
    private String status;

    @Builder
    public MenuOption(Long seq, String name, Integer price, String status) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
    }
}
