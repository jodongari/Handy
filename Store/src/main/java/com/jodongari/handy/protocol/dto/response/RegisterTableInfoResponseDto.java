package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.Builder;
import lombok.Value;

@Value
public class RegisterTableInfoResponseDto {

    Long storeSeq;
    String tableName;
    TableInfo.TableInfoStatus status;

    @Builder
    public RegisterTableInfoResponseDto(Long storeSeq, String tableName, TableInfo.TableInfoStatus status) {
        this.storeSeq = storeSeq;
        this.tableName = tableName;
        this.status = status;
    }

    public static RegisterTableInfoResponseDto of(TableInfoModel tableInfoModel) {
        return RegisterTableInfoResponseDto.builder()
                .storeSeq(tableInfoModel.getStoreSeq())
                .tableName(tableInfoModel.getTableName())
                .status(tableInfoModel.getStatus())
                .build();
    }
}
