package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import lombok.Data;

@Data
public class ManageTableInfoRequestDto {

    private Long seq;
    private Long storeSeq;
    private String tableName;
    private String tableHash;
    private TableInfo.TableInfoStatus status;

}
