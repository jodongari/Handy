package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ManageMenuRequestDto {
    private Long seq;
    private Long storeSeq;
    private String name;
    private String description;
    private String imageUrl;
    private String status; // TO-DO: 확인 필요
    private List<MenuOptionRequestDto> menuOptionModels;
    private List<ExtraOptionGroupRequestDto> extraOptionGroupModels;

}
