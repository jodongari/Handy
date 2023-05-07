package com.jodongari.handy.api.protocol.dto.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TableInfoModel {

    private Long seq;
    private Long storeSeq;
    private String tableName;
    private String tableHash;
    private String status;

    @Builder
    public TableInfoModel(Long seq, Long storeSeq, String tableName, String tableHash, String status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.tableName = tableName;
        this.tableHash = tableHash;
        this.status = status;
    }

}
