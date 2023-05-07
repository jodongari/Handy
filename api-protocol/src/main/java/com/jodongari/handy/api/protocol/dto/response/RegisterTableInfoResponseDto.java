package com.jodongari.handy.api.protocol.dto.response;

import lombok.Data;

@Data
public class RegisterTableInfoResponseDto {
    Long storeSeq;
    String tableName;
    String status;
}
