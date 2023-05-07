package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

@Data
public class ManageTableInfoRequestDto {

    private Long seq;
    private Long storeSeq;
    private String tableName;
    private String tableHash;
    private String status;

}
