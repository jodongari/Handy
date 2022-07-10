package com.jodongari.handy.protocol.requestDto;

import lombok.Value;

import java.util.List;

@Value
public class RegisterTableRequestDto {
    Long storeSeq;
    List<Integer> tableNumber;
}
