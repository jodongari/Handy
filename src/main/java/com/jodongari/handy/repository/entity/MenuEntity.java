package com.jodongari.handy.repository.entity;

import lombok.AccessLevel;
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
    String status;



}
