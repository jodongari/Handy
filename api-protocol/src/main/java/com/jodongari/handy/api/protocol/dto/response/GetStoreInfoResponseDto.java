package com.jodongari.handy.api.protocol.dto.response;

import lombok.Data;

@Data
public class GetStoreInfoResponseDto {
    Long seq;
    Long ownerSeq;
    String name;
    String openTime;
    String dayOff;
    String logoImageUrl;
    String category;
    String status;
}
