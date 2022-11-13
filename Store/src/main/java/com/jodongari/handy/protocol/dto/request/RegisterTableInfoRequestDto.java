package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.Value;

@Value
public class RegisterTableInfoRequestDto {
    Long storeSeq;
    String tableName;

    public TableInfoModel toModel() {
        return TableInfoModel.builder()
                .storeSeq(this.storeSeq)
                .tableName(this.tableName)
                .build();
    }
}
