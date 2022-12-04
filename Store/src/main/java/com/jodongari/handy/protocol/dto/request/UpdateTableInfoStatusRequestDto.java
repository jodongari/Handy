package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import lombok.Data;

@Data
public class UpdateTableInfoStatusRequestDto {
    Long tableSeq;
    TableInfo.TableInfoStatus status;
}
