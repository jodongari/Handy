package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.menu.vo.MenuStatus;
import com.jodongari.handy.protocol.dto.model.ExtraOptionGroupModel;
import com.jodongari.handy.protocol.dto.model.MenuOptionModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
public class GetMenuResponseDto {

    Long seq;
    Long storeSeq;
    String name;
    String description;
    String imageUrl;
    MenuStatus status;
    List<MenuOptionModel> menuOptionModels;
    List<ExtraOptionGroupModel> extraOptionGroupModels;

    @Builder
    public GetMenuResponseDto(Long seq, Long storeSeq, String name, String description, String imageUrl, MenuStatus status, List<MenuOptionModel> menuOptionModels, List<ExtraOptionGroupModel> extraOptionGroupModels) {
        this.seq = seq;
        this.storeSeq = storeSeq;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.menuOptionModels = menuOptionModels;
        this.extraOptionGroupModels = extraOptionGroupModels;
    }
}
