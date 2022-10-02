package com.jodongari.handy.protocol.dto.request;

import lombok.Value;

import java.util.Map;

@Value
public class ManageTableInfoRequestDto {
    Long storeSeq;
    Map<String, String> tableInfos;
}
