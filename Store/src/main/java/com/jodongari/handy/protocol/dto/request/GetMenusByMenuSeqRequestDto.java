package com.jodongari.handy.protocol.dto.request;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class GetMenusByMenuSeqRequestDto {
    List<Long> menuSeq;
}
