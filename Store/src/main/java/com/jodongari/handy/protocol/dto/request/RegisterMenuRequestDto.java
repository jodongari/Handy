package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RegisterMenuRequestDto {
     Long storeSeq;
     String name;
     String description;
     List<MenuOptionRequestDto> menuOptions;
     List<ExtraOptionGroupRequestDto> extraOptionGroups;
}
