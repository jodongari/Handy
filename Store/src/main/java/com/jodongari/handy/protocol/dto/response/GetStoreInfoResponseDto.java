package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.store.vo.StoreStatus;

public class GetStoreInfoResponseDto {
    Long storeSeq;
    Long ownerSeq;
    String name;
    String openTime;
    String dayOff;
    String logoImageUrl;
    String category;
    StoreStatus status;
}
