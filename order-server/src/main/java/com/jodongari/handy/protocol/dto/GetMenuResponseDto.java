package com.jodongari.handy.protocol.dto;

import com.jodongari.handy.protocol.model.ExtraOptionGroupModel;
import com.jodongari.handy.protocol.model.MenuOptionModel;
import lombok.Data;

import java.util.List;

@Data
public class GetMenuResponseDto {
    Long seq;
    Long storeSeq;
    String name;
    String description;
    String imageUrl;
    String status;
    List<MenuOptionModel> menuOptionModels;
    List<ExtraOptionGroupModel> extraOptionGroupModels;
}
