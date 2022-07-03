package com.jodongari.handy.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MENU_OPTION")
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
    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MenuEntity> a;

    @Builder
    public MenuOptionEntity(Long seq, String name, Integer price, String status, List<MenuEntity> a) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
        this.a = a;
    }
}
