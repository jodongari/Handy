package com.jodongari.handy.protocol.model.menu;

import com.jodongari.handy.domain.entity.MenuEntity;
import com.jodongari.handy.domain.entity.status.MenuStatus;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    private Long seq;
    private String name;
    private String description;
    private String imageURL;
    private MenuStatus status;
    private List<MenuOption> menuOptions;
    private List<ExtraOptionGroup> extraOptionGroups;

    @Builder
    public Menu(Long seq, String name, String description, String imageURL,
                MenuStatus status, List<MenuOption> menuOptions, List<ExtraOptionGroup> extraOptionGroups) {
        this.seq = seq;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.status = status;
        this.menuOptions = menuOptions;
        this.extraOptionGroups = extraOptionGroups;
    }

    public MenuEntity dtoToMenuEntity(Long storeSeq, String imageURL) {
        return MenuEntity.builder()
                .storeSeq(storeSeq)
                .image(imageURL)
                .description(this.description)
                .status(this.status)
                .build();
    }
}
