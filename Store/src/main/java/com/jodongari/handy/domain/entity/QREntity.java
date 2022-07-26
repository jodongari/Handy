package com.jodongari.handy.domain.entity;

import com.jodongari.handy.domain.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "QR")
public class QREntity extends BaseTimeEntity implements Persistable<String> {

    @Id
    @Column(name = "HASH", length = 64)
    private String hash;

    @Column(name = "STORE_SEQ", nullable = false)
    private Long storeSeq;

    @Column(name = "TABLE_NAME", length = 10, nullable = false)
    private String tableName;

    @Builder
    public QREntity(String hash, Long storeSeq, String tableName) {
        this.hash = hash;
        this.storeSeq = storeSeq;
        this.tableName = tableName;
    }

    public void updateTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return this.hash;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedAt() == null;
    }
}
