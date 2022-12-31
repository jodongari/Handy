package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.menu.vo.MenuStatus;
import lombok.Data;

import java.util.List;

@Data
public class RegisterMenuRequestDto {
    private Long seq;
    private Long storeSeq;
    private String name;
    private String description;
    private String imageUrl;
    private MenuStatus status; // TO-DO: 확인 필요
    private List<MenuOptionRequestDto> menuOptionModels;
    private List<ExtraOptionGroupRequestDto> extraOptionGroupModels;
}
