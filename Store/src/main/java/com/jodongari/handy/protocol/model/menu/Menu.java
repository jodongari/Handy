package com.jodongari.handy.protocol.model.menu;

import lombok.*;

import java.util.List;

@Getter
public class Menu {

    private Long seq;
    private String name;
    private String description;
    private String imageURL;
    private String status;
    private List<MenuOption> menuOptions;
    private List<ExtraOptionGroup> extraOptionGroups;

    @Builder
    public Menu(Long seq, String name, String description, String imageURL, String status, List<MenuOption> menuOptions, List<ExtraOptionGroup> extraOptionGroups) {
        this.seq = seq;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.status = status;
        this.menuOptions = menuOptions;
        this.extraOptionGroups = extraOptionGroups;
    }
}
