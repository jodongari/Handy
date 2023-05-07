package com.jodongari.handy.api.protocol.dto.response;

import com.jodongari.handy.api.protocol.dto.model.ExtraOptionGroupModel;
import com.jodongari.handy.api.protocol.dto.model.MenuOptionModel;
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
