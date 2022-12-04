package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class ManageTableInfoRequestDto {
    Long storeSeq;
    Map<String, String> tableInfos;
}
