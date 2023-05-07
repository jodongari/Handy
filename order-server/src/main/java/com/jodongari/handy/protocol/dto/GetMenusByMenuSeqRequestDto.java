package com.jodongari.handy.protocol.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetMenusByMenuSeqRequestDto {
    List<Long> menuSeq;
}
