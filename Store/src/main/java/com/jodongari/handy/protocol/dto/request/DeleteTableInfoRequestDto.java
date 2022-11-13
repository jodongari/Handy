package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.Value;

@Value
public class DeleteTableInfoRequestDto {
    Long tableSeq;

    public TableInfoModel toModel() {
        return TableInfoModel.builder()
                .seq(tableSeq)
                .build();
    }
}
