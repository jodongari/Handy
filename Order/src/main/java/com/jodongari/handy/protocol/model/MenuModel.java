package com.jodongari.handy.protocol.model;

import lombok.Data;

import java.util.List;

@Data
public class MenuModel {
    Long seq;
    Integer count;
    String name;
    MenuOptionModel menuOption;
    List<ExtraOptionGroupModel> extraOptionGroups;
}
