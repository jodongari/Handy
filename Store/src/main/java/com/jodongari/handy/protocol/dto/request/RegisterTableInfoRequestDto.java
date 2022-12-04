package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

@Data
public class RegisterTableInfoRequestDto {
    Long storeSeq;
    String tableName;
}
