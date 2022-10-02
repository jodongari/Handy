package com.jodongari.handy.protocol;

import com.jodongari.handy.infrastructure.entity.status.MenuStatus;
import lombok.Value;

import java.util.List;

@Value
public class MenuDTO {

     Long seq;

     Long storeSeq;

     String name;

     String description;

     String imageUrl;

     MenuStatus status; // TO-DO: 확인 필요

     List<MenuOptionDTO> menuOptionDTOs;

     List<ExtraOptionGroupDTO> extraOptionGroupDTOs;

}
