package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.menu.vo.ExtraOptionStatus;
import lombok.Data;

@Data
public class ExtraOptionRequestDto {
    Long seq;
    String name;
    Integer extraPrice;
    ExtraOptionStatus status;
    Long extraOptionGroupSeq;

}
