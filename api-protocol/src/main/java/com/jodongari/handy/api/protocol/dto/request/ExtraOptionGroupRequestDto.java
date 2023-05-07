package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ExtraOptionGroupRequestDto {
    Long seq;
    String name;
    String type;
    Integer minSelectLimit;
    Integer maxSelectLimit;
    String status;
    Long menuSeq;
    List<ExtraOptionRequestDto> extraOptionModels;
}
