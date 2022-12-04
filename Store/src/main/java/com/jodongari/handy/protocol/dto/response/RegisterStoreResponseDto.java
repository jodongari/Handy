package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.store.Store;
import com.jodongari.handy.domain.store.vo.StoreStatus;
import lombok.Data;

@Data
public class RegisterStoreResponseDto {
    Long storeSeq;
    Long ownerSeq;
    String name;
    String businessReportCardImageUrl;
    String businessLicenseImageUrl;
    String businessName;
    String businessPersonName;
    String businessNumber;
    String businessAddress;
    String address;
    String telNumber;
    String introduction;
    String openTime;
    String dayOff;
    String originCountry;
    String logoImageUrl;
    String backgroundImageUrl;
    Integer tableCount;
    String category;
}
