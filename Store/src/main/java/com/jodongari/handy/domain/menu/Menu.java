package com.jodongari.handy.domain.menu;

import com.jodongari.handy.domain.menu.vo.ExtraOptionStatus;
import com.jodongari.handy.domain.menu.vo.MenuDescription;
import com.jodongari.handy.domain.menu.vo.MenuImage;
import com.jodongari.handy.domain.menu.vo.MenuName;
import com.jodongari.handy.domain.menu.vo.MenuOptionStatus;
import com.jodongari.handy.domain.menu.vo.MenuStatus;
import com.jodongari.handy.protocol.dto.model.MenuModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final List<MenuOption> menuOptions = new ArrayList<>();

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final List<ExtraOptionGroup> extraOptionGroups = new ArrayList<>();

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
        List<MenuOption> menuOptions = menuModel.getMenuOptionModels()
                .stream()
                .map(menuOptionModel -> {
                    menuOptionModel.setStatus(MenuOptionStatus.OPEN);
                    return menuOptionModel.toEntity();
                })
                .collect(Collectors.toList());

        List<ExtraOptionGroup> extraOptionGroups = menuModel.getExtraOptionGroupModels()
                .stream()
                .map(extraOptionGroupModel -> {
                    ExtraOptionGroup extraOptionGroup = extraOptionGroupModel.toEntity();
                    List<ExtraOption> extraOptions = extraOptionGroupModel.getExtraOptionModels()
                            .stream()
                            .map(extraOptionModel -> {
                                extraOptionModel.setStatus(ExtraOptionStatus.OPEN);
                                return extraOptionModel.toEntity();
                            })
                            .collect(Collectors.toList());
                    extraOptionGroup.addAllExtraOption(extraOptions);
                    return extraOptionGroup;
                })
                .collect(Collectors.toList());

        Menu menu = Menu.builder()
                .storeSeq(menuModel.getStoreSeq())
                .name(MenuName.create(menuModel.getName()))
                .description(MenuDescription.create(menuModel.getDescription()))
//                .image(MenuImage.create(menuModel.getImageUrl()))
                .image(MenuImage.create("null"))
                .status(MenuStatus.READY)
                .build();

        menu.addAllMenuOption(menuOptions);
        menu.addAllExtraOptionGroup(extraOptionGroups);
        return menu;
    }

    public void addMenuOption(MenuOption menuOption) {
        this.getMenuOptions().add(menuOption);
        menuOption.addMenuEntity(this);
    }

    public void addAllMenuOption(List<MenuOption> menuOptions) {
        for (MenuOption menuOption : menuOptions) {
            this.addMenuOption(menuOption);
        }
    }

    public void addExtraOptionGroup(ExtraOptionGroup extraOptionGroup) {
        this.getExtraOptionGroups().add(extraOptionGroup);
        extraOptionGroup.addMenu(this);
    }

    public void addAllExtraOptionGroup(List<ExtraOptionGroup> extraOptionGroups) {
        for (ExtraOptionGroup extraOptionGroupEntity : extraOptionGroups) {
            this.addExtraOptionGroup(extraOptionGroupEntity);
        }
    }
}
