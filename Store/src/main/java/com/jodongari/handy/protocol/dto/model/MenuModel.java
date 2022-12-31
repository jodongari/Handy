package com.jodongari.handy.protocol.dto.model;

import com.jodongari.handy.domain.menu.vo.MenuStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MenuModel {

    private Long seq;
    private Long storeSeq;
    private String name;
    private String description;
    private String imageUrl;
    private MenuStatus status; // TO-DO: 확인 필요
    private List<MenuOptionModel> menuOptionModels;
    private List<ExtraOptionGroupModel> extraOptionGroupModels;

    @Builder
    public MenuModel(Long seq, Long storeSeq, String name, String description, String imageUrl, MenuStatus status, List<MenuOptionModel> menuOptionModels, List<ExtraOptionGroupModel> extraOptionGroupModels) {
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
