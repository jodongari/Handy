package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ExtraOptionGroupRequestDto {
    Long extraOptionGroupSeq;
    String name;
    String type;
    Integer minSelectLimit;
    Integer maxSelectLimit;
    List<ExtraOptionRequestDto> extraOptions;
}
