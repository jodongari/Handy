package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.menu.vo.MenuOptionStatus;
import lombok.Data;

@Data
public class MenuOptionRequestDto {
    Long seq;
    String name;
    Integer price;
    MenuOptionStatus status;
    Long menuSeq;
}
