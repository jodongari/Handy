package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.menu.vo.ExtraOptionGroupStatus;
import lombok.Data;

import java.util.List;

@Data
public class ExtraOptionGroupRequestDto {
    Long seq;
    String name;
    String type;
    Integer minSelectLimit;
    Integer maxSelectLimit;
    ExtraOptionGroupStatus status;
    Long menuSeq;
    List<ExtraOptionRequestDto> extraOptionModels;
}
