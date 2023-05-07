package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class GetMenusByMenuSeqRequestDto {
    List<Long> menuSeq;
}
