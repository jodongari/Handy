package com.jodongari.handy.domain.entity;

import com.jodongari.handy.domain.entity.status.MenuStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MENU")
public class MenuEntity {
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

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MenuOptionEntity> menuOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ExtraOptionGroupEntity> extraOptionGroupEntities = new ArrayList<>();

    @Builder
    public MenuEntity(Long seq, Long storeSeq, String name, String description, String image, MenuStatus status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public void addMenuOption(MenuOptionEntity menuOptionEntity) {
        this.getMenuOptionEntities().add(menuOptionEntity);
        menuOptionEntity.addMenuEntity(this);
    }

    public void addAllMenuOption(List<MenuOptionEntity> menuOptionEntities) {
        for(MenuOptionEntity menuOptionEntity : menuOptionEntities) {
            this.addMenuOption(menuOptionEntity);
        }
    }

    public void addExtraOptionGroup(ExtraOptionGroupEntity extraOptionGroupEntity) {
        this.getExtraOptionGroupEntities().add(extraOptionGroupEntity);
        extraOptionGroupEntity.addMenu(this);
    }

    public void addAllExtraOptionGroup(List<ExtraOptionGroupEntity> extraOptionGroupEntities) {
        for(ExtraOptionGroupEntity extraOptionGroupEntity : extraOptionGroupEntities) {
            this.addExtraOptionGroup(extraOptionGroupEntity);
        }
    }

}
