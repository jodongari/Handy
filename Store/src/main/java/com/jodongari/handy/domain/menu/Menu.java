package com.jodongari.handy.domain.menu;

import com.jodongari.handy.domain.menu.vo.MenuDescription;
import com.jodongari.handy.domain.menu.vo.MenuImage;
import com.jodongari.handy.domain.menu.vo.MenuName;
import com.jodongari.handy.domain.menu.vo.MenuStatus;
import com.jodongari.handy.protocol.dto.model.MenuModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "MENU")
@EqualsAndHashCode
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "STORE_SEQ", nullable = false)
    private Long storeSeq;

    @Embedded
    private MenuName name;

    @Embedded
    private MenuDescription description;

    @Embedded
    private MenuImage image;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MenuStatus status;

    private static final MenuStatus MENU_CREATED = MenuStatus.READY;

    @Builder
    public Menu(Long seq, Long storeSeq, MenuName name, MenuDescription description, MenuImage image, MenuStatus status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public static Menu create(MenuModel menuModel) {
        Menu menu = Menu.builder()
                .storeSeq(menuModel.getStoreSeq())
                .name(MenuName.create(menuModel.getName()))
                .description(MenuDescription.create(menuModel.getDescription()))
//                .image(MenuImage.create(menuModel.getImageUrl()))
                .image(MenuImage.create("null"))
                .status(MENU_CREATED)
                .build();

        return menu;
    }

    public static Menu merge(MenuModel menuModel) {
        Menu menu = Menu.builder()
                .seq(menuModel.getSeq())
                .storeSeq(menuModel.getStoreSeq())
                .name(MenuName.create(menuModel.getName()))
                .description(MenuDescription.create(menuModel.getDescription()))
//                .image(MenuImage.create(menuModel.getImageUrl()))
                .image(MenuImage.create("null"))
                .status(menuModel.getStatus())
                .build();

        return menu;
    }
}
