package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

@Data
public class UpdateTableInfoRequestDto {
    Long seq;
    String tableName;
    String status;
}
