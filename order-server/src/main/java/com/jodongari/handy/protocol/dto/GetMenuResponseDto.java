package com.jodongari.handy.protocol.dto;

import com.jodongari.handy.domain.menu.vo.MenuStatus;
import com.jodongari.handy.protocol.dto.model.ExtraOptionGroupModel;
import com.jodongari.handy.protocol.dto.model.MenuOptionModel;
import lombok.Data;

import java.util.List;

@Data
public class GetMenuResponseDto {
    Long seq;
    Long storeSeq;
    String name;
    String description;
    String imageUrl;
    MenuStatus status;
    List<MenuOptionModel> menuOptionModels;
    List<ExtraOptionGroupModel> extraOptionGroupModels;
}
