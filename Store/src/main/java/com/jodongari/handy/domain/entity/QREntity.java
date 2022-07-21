package com.jodongari.handy.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "QR")
public class QREntity {

    @Id
    @Column(name = "HASH", length = 64)
    private String hash;

    @Column(name = "STORE_SEQ", nullable = false)
    private Long storeSeq;

    @Column(name = "TABLE_NUMBER", nullable = false)
    private Integer tableNumber;

    @Builder
    public QREntity(String hash, Long storeSeq, Integer tableNumber) {
        this.hash = hash;
        this.storeSeq = storeSeq;
        this.tableNumber = tableNumber;
    }
}
