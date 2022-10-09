package com.jodongari.handy.protocol.dto.model;

import com.jodongari.handy.domain.menu.vo.*;
import com.jodongari.handy.domain.store.vo.StoreSequence;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class MenuModel {

     Long seq;
     Long storeSeq;
     String name;
     String description;
     String imageUrl;
     MenuStatus status; // TO-DO: 확인 필요
     List<MenuOptionModel> menuOptionModels;
     List<ExtraOptionGroupModel> extraOptionGroupModels;

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
