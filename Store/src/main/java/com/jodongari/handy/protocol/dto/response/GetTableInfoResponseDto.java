package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import lombok.Builder;
import lombok.Value;

@Value
public class GetTableInfoResponseDto {
    private Long seq;
    private Long storeSeq;
    private String tableName;
    private String tableHash;
    private TableInfo.TableInfoStatus status;

    @Builder
    public GetTableInfoResponseDto(Long seq, Long storeSeq, String tableName, String tableHash, TableInfo.TableInfoStatus status) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.tableName = tableName;
        this.tableHash = tableHash;
        this.status = status;
    }
}
