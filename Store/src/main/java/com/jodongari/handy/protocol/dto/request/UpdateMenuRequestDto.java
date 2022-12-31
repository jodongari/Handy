package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.menu.vo.MenuStatus;
import lombok.Data;

import java.util.List;

@Data
public class UpdateMenuRequestDto {
    Long storeSeq;
    String name;
    String description;
    MenuStatus status;
    List<MenuOptionRequestDto> menuOptionModels;
    List<ExtraOptionGroupRequestDto> extraOptionGroupModels;
}
