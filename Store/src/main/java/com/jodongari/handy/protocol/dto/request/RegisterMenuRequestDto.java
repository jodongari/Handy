package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.Menu;
import com.jodongari.handy.domain.menu.MenuOption;
import com.jodongari.handy.infrastructure.entity.status.MenuStatus;
import lombok.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Value
public class RegisterMenuRequestDto {

     Long seq;

     Long storeSeq;

     String name;

     String description;

     String image;

     MenuStatus status;

     List<MenuOption> menuOptionDTOs;

     List<ExtraOptionGroup> extraOptionGroupDTOs;

}
