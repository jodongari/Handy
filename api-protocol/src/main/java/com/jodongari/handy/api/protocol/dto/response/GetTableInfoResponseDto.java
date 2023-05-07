package com.jodongari.handy.api.protocol.dto.response;

import lombok.Data;

@Data
public class GetTableInfoResponseDto {
    Long seq;
    Long storeSeq;
    String tableName;
    String tableHash;
    String status;
}
