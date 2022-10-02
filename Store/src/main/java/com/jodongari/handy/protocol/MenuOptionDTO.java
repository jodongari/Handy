package com.jodongari.handy.protocol;

import com.jodongari.handy.infrastructure.entity.status.MenuOptionStatus;
import lombok.Value;

@Value
public class MenuOptionDTO {

     Long seq;

     String name;

     Integer price;

     MenuOptionStatus status; // TODO: 확인

}
