package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

@Data
public class RegisterStoreRequestDto {
    Long ownerSeq;
    String name;
    String businessName;
    String businessReportCardImageUrl;
    String businessLicenseImageUrl;
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
    String category;
}
