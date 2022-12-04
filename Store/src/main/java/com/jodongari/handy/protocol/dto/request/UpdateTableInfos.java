package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import lombok.Data;

@Data
public class UpdateTableInfos {
    Long seq;
    String tableName;
    TableInfo.TableInfoStatus status;
}
