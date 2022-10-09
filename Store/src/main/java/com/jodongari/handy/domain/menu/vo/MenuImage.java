package com.jodongari.handy.domain.menu.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MenuImage {

    @Column(name = "IMAGE", nullable = false, length = 200)
    private String image;

    protected MenuImage() {};

    private MenuImage(String image) {
        this.image = image;
    }

    public static MenuImage create(String imageUrl) {
        return new MenuImage(imageUrl);
    }

    public String getValue() {
        return this.image;
    }
}
