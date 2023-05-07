package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMenuRequestDto {
    Long storeSeq;
    String name;
    String description;
    String status;
    List<MenuOptionRequestDto> menuOptionModels;
    List<ExtraOptionGroupRequestDto> extraOptionGroupModels;
}
