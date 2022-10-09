package com.jodongari.handy.domain.menu.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MenuName {

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    protected MenuName() {};

    private MenuName(String name) {
        this.name = name;
    }

    public static MenuName create(String name) {
        return new MenuName(name);
    }

    public String getValue() {
        return name;
    }
}
