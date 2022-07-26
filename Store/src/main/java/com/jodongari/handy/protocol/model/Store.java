package com.jodongari.handy.protocol.model;

import com.jodongari.handy.domain.entity.StoreEntity;
import com.jodongari.handy.domain.entity.status.StoreStatus;
import lombok.Data;

@Data
public class Store {
    Long seq;
    Long ownerSeq;
    String name;
    String businessReportCardImage;
    String businessLicenseImage;
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
    Integer tableCount;
    String category;
    StoreStatus status;

    public Store(StoreEntity entity) {
        this.seq = entity.getSeq();
        this.ownerSeq = entity.getOwnerSeq();
        this.name = entity.getName();
        this.businessReportCardImage = entity.getBusinessReportCardImage();
        this.businessLicenseImage = entity.getBusinessLicenseImage();
        this.businessName = entity.getBusinessName();
        this.businessPersonName = entity.getBusinessPersonName();
        this.businessNumber = entity.getBusinessNumber();
        this.businessAddress = entity.getBusinessAddress();
        this.address = entity.getAddress();
        this.telNumber = entity.getTelNumber();
        this.introduction = entity.getIntroduction();
        this.openTime = entity.getOpenTime();
        this.dayOff = entity.getDayOff();
        this.originCountry = entity.getOriginCountry();
        this.logo = entity.getLogo();
        this.backgroundImage = entity.getBackgroundImage();
        this.tableCount = entity.getTableCount();
        this.category = entity.getCategory();
        this.status = entity.getStatus();
    }
}
