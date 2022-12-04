package com.jodongari.handy.domain.tableInfo;

import com.jodongari.handy.domain.base.BaseTimeEntity;
import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
@Table(name = "TABLE_INFO")
public class TableInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "STORE_SEQ", nullable = false)
    private Long storeSeq;

    @Column(name = "TABLE_NAME", length = 10, nullable = false)
    private String tableName;

    @Column(name = "TABLE_HASH", length = 64)
    private String tableHash;

    @Column(name = "STATUS", nullable = false, length = 8)
    @Enumerated(EnumType.STRING)
    private TableInfoStatus status;

    @Builder
    public TableInfo(Long seq, Long storeSeq, String tableName, String tableHash, TableInfoStatus status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.tableName = tableName;
        this.tableHash = tableHash;
        this.status = status;
    }

    public static TableInfo create(TableInfoModel tableInfoModel) {
        return TableInfo.builder()
                .seq(tableInfoModel.getSeq())
                .storeSeq(tableInfoModel.getStoreSeq())
                .tableName(tableInfoModel.getTableName())
                .tableHash(tableInfoModel.getTableHash())
                .status(TABLE_INFO_CREATED)
                .build();
    }

    public TableInfoModel toModel() {
        return TableInfoModel.builder()
                .seq(this.getSeq())
                .storeSeq(this.getStoreSeq())
                .tableName(this.getTableName())
                .tableHash(this.getTableHash())
                .status(this.getStatus())
                .build();
    }

    public static enum TableInfoStatus {
        ACTIVE,
        INACTIVE,
        DELETE
    }
    private static final TableInfoStatus TABLE_INFO_CREATED = TableInfoStatus.ACTIVE;

    public void updateTableInfoName(String tableName) {
        this.tableName = tableName;
    }

    public void updateTableInfoStatus(TableInfoStatus status) {
        this.status = status;
    }

    public void updateTableInfo(String tableName,
                                TableInfoStatus status){
        this.tableName = tableName;
        this.status = status;
    }
}
