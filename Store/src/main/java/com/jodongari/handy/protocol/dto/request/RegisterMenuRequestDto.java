package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.protocol.dto.model.ExtraOptionGroupModel;
import com.jodongari.handy.protocol.dto.model.MenuModel;
import com.jodongari.handy.protocol.dto.model.MenuOptionModel;
import lombok.Value;

import java.util.List;

@Value
public class RegisterMenuRequestDto {

     Long storeSeq;
     String name;
     String description;
     List<MenuOptionModel> menuOptionModels;
     List<ExtraOptionGroupModel> extraOptionGroupModels;

     public MenuModel toModel() {
          return MenuModel.builder()
                  .storeSeq(this.storeSeq)
                  .name(this.name)
                  .description(this.description)
                  .menuOptionModels(menuOptionModels)
                  .extraOptionGroupModels(extraOptionGroupModels)
                  .build();
     }
}
