package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.Value;

@Value
public class UpdateTableInfoStatusRequestDto {
    Long tableSeq;
    TableInfo.TableInfoStatus status;

    public TableInfoModel toModel() {
        return TableInfoModel.builder()
                .seq(this.tableSeq)
                .status(this.status)
                .build();
    }
}
