package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

@Data
public class MenuOptionRequestDto {
    Long seq;
    String name;
    Integer price;
    String status;
    Long menuSeq;
}
