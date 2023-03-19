package com.jodongari.handy.protocol.model;

import lombok.Data;

import java.util.List;

@Data
public class ExtraOptionGroupModel {

    Long seq;
    List<ExtraOptionModel> extraOptions;
}
