package com.jodongari.handy.domain.requestDto;

import com.jodongari.handy.domain.menu.ExtraOption;
import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.MenuOption;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterMenuRequestDto {
    Long storeSeq;
    String name;
    String description;
    List<MenuOption> menuOptions;
    List<ExtraOptionGroup> extraOptionGroups;
}
