package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

@Data
public class ExtraOptionRequestDto {
    Long seq;
    String name;
    Integer extraPrice;
    String status;
    Long extraOptionGroupSeq;

}
