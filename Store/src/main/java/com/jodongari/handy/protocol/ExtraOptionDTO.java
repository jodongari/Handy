package com.jodongari.handy.protocol;

import com.jodongari.handy.infrastructure.entity.status.ExtraOptionStatus;
import lombok.Value;

@Value
public class ExtraOptionDTO {

    private Long seq;

    private String name;

    private Integer extraFee;

    private ExtraOptionStatus status;
}
