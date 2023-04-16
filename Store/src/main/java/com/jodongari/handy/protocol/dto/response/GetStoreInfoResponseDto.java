package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.store.vo.StoreStatus;
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
    StoreStatus status;
}
