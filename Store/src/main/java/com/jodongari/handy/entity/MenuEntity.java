package com.jodongari.handy.entity;

import com.jodongari.handy.entity.status.MenuStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    Long storeSeq;

    @Column(name = "NAME", nullable = false, length = 50)
    String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    String description;

    @Column(name = "IMAGE", nullable = false, length = 200)
    String image;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    MenuStatus status;

    @Builder
    public MenuEntity(Long seq, Long storeSeq, String name, String description, String image, MenuStatus status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }
}
