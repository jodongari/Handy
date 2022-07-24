package com.jodongari.handy.protocol.requestDto;

import com.jodongari.handy.domain.entity.StoreEntity;
import com.jodongari.handy.domain.entity.status.StoreStatus;
import lombok.Value;

@Value
public class RegisterStoreRequestDto {
    Long ownerSeq;
    String name;
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
    String logo;
    String backgroundImage;
    int tableCount;
    String category;
    StoreStatus storeStatus;

    public StoreEntity dtoToEntity(String storeImageKey,
                                   String businessReportCardImageKey,
                                   String businessLicenseImageKey,
                                   String logoImageKey){
        return StoreEntity.builder()
                .ownerSeq(this.ownerSeq)
                .name(this.name)
                .businessReportCardImage(businessReportCardImageKey)
                .businessLicenseImage(businessLicenseImageKey)
                .businessName(this.businessName)
                .businessPersonName(this.businessPersonName)
                .businessAddress(this.businessAddress)
                .address(this.address)
                .telNumber(this.telNumber)
                .introduction(this.introduction)
                .openTime(this.openTime)
                .dayOff(this.dayOff)
                .originCountry(this.originCountry)
                .logo(logoImageKey)
                .backgroundImage(storeImageKey)
                .tableCount(this.tableCount)
                .category(this.category)
                .status(StoreStatus.READY)
                .build();
    }

}
