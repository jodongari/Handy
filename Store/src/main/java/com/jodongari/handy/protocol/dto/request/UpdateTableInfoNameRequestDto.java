package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.Value;

@Value
public class UpdateTableInfoNameRequestDto {
    Long tableSeq;
    String tableName;

    public TableInfoModel toModel() {
        return TableInfoModel.builder()
                .seq(this.tableSeq)
                .tableName(this.tableName)
                .build();
    }
}
