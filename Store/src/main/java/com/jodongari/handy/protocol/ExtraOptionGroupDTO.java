package com.jodongari.handy.protocol;

import com.jodongari.handy.infrastructure.entity.status.ExtraOptionGroupStatus;
import lombok.Value;

import java.util.List;

@Value
public class ExtraOptionGroupDTO {

     Long seq;

     String name;

     String type;

     Integer minSelectLimit;

     Integer maxSelectLimit;

     ExtraOptionGroupStatus status; // TODO 확인 필요

     List<ExtraOptionDTO> extraOptionDTOs;
    
}
