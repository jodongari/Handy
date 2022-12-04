package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import lombok.Data;

@Data
public class UpdateTableInfoNameRequestDto {
    Long tableSeq;
    String tableName;
}
