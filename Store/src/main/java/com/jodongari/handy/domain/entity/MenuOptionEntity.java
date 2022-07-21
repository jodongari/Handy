package com.jodongari.handy.domain.entity;

import com.jodongari.handy.domain.entity.status.MenuOptionStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MENU_OPTION")
@EqualsAndHashCode
public class MenuOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MenuOptionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuSeq")
    private MenuEntity menuEntity;

    @Builder
    public MenuOptionEntity(Long seq, String name, Integer price, MenuOptionStatus status) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
        this.menuEntity = menuEntity;
    }

    public void addMenuEntity(MenuEntity menuEntity) {
        this.menuEntity = menuEntity;
    }
}
