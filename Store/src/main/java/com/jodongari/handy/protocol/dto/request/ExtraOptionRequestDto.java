package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

@Data
public class ExtraOptionRequestDto {
    Long extraOptionSeq;
    String name;
    Integer extraPrice;
}
