package com.jodongari.handy.domain.menu;

import com.jodongari.handy.infrastructure.entity.status.MenuStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @Column(name = "IMAGE", nullable = false, length = 200)
    private String image;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MenuStatus status;

    @OneToMany(mappedBy = "menuEntity", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final List<MenuOption> menuOptions = new ArrayList<>();

    @OneToMany(mappedBy = "menuEntity", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final List<ExtraOptionGroup> extraOptionGroups = new ArrayList<>();

    @Builder
    public Menu(Long seq, Long storeSeq, String name, String description, String image, MenuStatus status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public void addMenuOption(MenuOption menuOption) {
        this.getMenuOptions().add(menuOption);
        menuOption.addMenuEntity(this);
    }

    public void addAllMenuOption(List<MenuOption> menuOptions) {
        for(MenuOption menuOptionEntity : menuOptions) {
            this.addMenuOption(menuOptionEntity);
        }
    }

    public void addExtraOptionGroup(ExtraOptionGroup extraOptionGroupEntity) {
        this.getExtraOptionGroups().add(extraOptionGroupEntity);
        extraOptionGroupEntity.addMenu(this);
    }

    public void addAllExtraOptionGroup(List<ExtraOptionGroup> extraOptionGroups) {
        for(ExtraOptionGroup extraOptionGroupEntity : extraOptionGroups) {
            this.addExtraOptionGroup(extraOptionGroupEntity);
        }
    }

}
