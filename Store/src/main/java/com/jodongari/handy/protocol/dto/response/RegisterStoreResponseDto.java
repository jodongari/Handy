package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.store.Store;
import com.jodongari.handy.domain.store.vo.StoreStatus;
import lombok.Value;

@Value
public class RegisterStoreResponseDto {
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
    StoreStatus status;

    public RegisterStoreResponseDto(Store storeEntity) {
        this.seq = storeEntity.getSeq();
        this.ownerSeq = storeEntity.getOwnerSeq();
        this.name = storeEntity.getName();
        this.businessReportCardImageUrl = storeEntity.getBusinessReportCardImageUrl();
        this.businessLicenseImageUrl = storeEntity.getBusinessLicenseImageUrl();
        this.businessName = storeEntity.getBusinessName();
        this.businessPersonName = storeEntity.getBusinessPersonName();
        this.businessNumber = storeEntity.getBusinessNumber();
        this.businessAddress = storeEntity.getBusinessAddress();
        this.address = storeEntity.getAddress();
        this.telNumber = storeEntity.getTelNumber();
        this.introduction = storeEntity.getIntroduction();
        this.openTime = storeEntity.getOpenTime();
        this.dayOff = storeEntity.getDayOff();
        this.originCountry = storeEntity.getOriginCountry();
        this.logoImageUrl = storeEntity.getLogoImageUrl();
        this.backgroundImageUrl = storeEntity.getBackgroundImageUrl();
        this.tableCount = storeEntity.getTableCount();
        this.category = storeEntity.getCategory();
        this.status = storeEntity.getStatus();
    }
}
