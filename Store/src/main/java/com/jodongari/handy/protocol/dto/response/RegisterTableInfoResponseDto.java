package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.Builder;
import lombok.Data;

@Data
public class RegisterTableInfoResponseDto {
    Long storeSeq;
    String tableName;
    TableInfo.TableInfoStatus status;
}
