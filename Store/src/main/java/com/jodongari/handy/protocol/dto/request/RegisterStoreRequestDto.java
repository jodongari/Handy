package com.jodongari.handy.protocol.dto.request;

import com.jodongari.handy.domain.store.vo.StoreStatus;
import com.jodongari.handy.protocol.dto.model.StoreModel;
import lombok.Value;

@Value
public class RegisterStoreRequestDto {
    Long ownerSeq;
    String name;
    String businessName;
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
    int tableCount;
    String category;
    StoreStatus storeStatus;

    public StoreModel toModel() {
        return StoreModel.builder()
                .ownerSeq(this.ownerSeq)
                .name(this.name)
                .businessLicenseImageUrl(this.businessLicenseImageUrl)
                .businessName(this.businessName)
                .businessPersonName(this.businessPersonName)
                .businessNumber(this.businessNumber)
                .address(this.address)
                .telNumber(this.telNumber)
                .introduction(this.introduction)
                .openTime(this.openTime)
                .dayOff(this.dayOff)
                .originCountry(this.originCountry)
                .logoImageUrl(this.logoImageUrl)
                .backgroundImageUrl(this.backgroundImageUrl)
                .tableCount(this.tableCount)
                .category(this.category)
                .status(this.storeStatus)
                .build();
    }
}
