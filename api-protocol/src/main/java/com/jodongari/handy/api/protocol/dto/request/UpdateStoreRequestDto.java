package com.jodongari.handy.api.protocol.dto.request;

import lombok.Data;

@Data
public class UpdateStoreRequestDto {
    Long storeSeq;
    String name;
    String address;
    String telNumber;
    String introduction;
    String openTime;
    String dayOff;
    String originCountry;
    String category;
}
