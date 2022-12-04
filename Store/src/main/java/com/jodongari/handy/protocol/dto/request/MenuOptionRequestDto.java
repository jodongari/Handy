package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

@Data
public class MenuOptionRequestDto {
    Long menuOptionSeq;
    String name;
    Integer price;
}
