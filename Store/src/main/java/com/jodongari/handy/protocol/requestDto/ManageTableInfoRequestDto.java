package com.jodongari.handy.protocol.requestDto;

import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class ManageTableInfoRequestDto {
    Long storeSeq;
    Map<String, String> tableInfos;
}
