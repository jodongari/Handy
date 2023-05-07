package com.jodongari.handy.api.protocol.dto.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreModel {

    Long seq;
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
    String status;

    @Builder
    public StoreModel(Long seq, Long ownerSeq, String name, String businessReportCardImageUrl, String businessLicenseImageUrl, String businessName, String businessPersonName, String businessNumber, String businessAddress, String address, String telNumber, String introduction, String openTime, String dayOff, String originCountry, String logoImageUrl, String backgroundImageUrl, Integer tableCount, String category, String status) {
        this.seq = seq;
        this.ownerSeq = ownerSeq;
        this.name = name;
        this.businessReportCardImageUrl = businessReportCardImageUrl;
        this.businessLicenseImageUrl = businessLicenseImageUrl;
        this.businessName = businessName;
        this.businessPersonName = businessPersonName;
        this.businessNumber = businessNumber;
        this.businessAddress = businessAddress;
        this.address = address;
        this.telNumber = telNumber;
        this.introduction = introduction;
        this.openTime = openTime;
        this.dayOff = dayOff;
        this.originCountry = originCountry;
        this.logoImageUrl = logoImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.tableCount = tableCount;
        this.category = category;
        this.status = status;
    }

}
