package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.store.vo.StoreStatus;
import com.jodongari.handy.protocol.dto.model.StoreModel;
import lombok.Builder;
import lombok.Value;

@Value
public class GetStoreResponseDto {

    Long seq;
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
    String logoImageUrl;
    String backgroundImageUrl;
    Integer tableCount;
    String category;
    StoreStatus status;

    @Builder
    public GetStoreResponseDto(Long seq, Long ownerSeq, String name, String businessName, String businessPersonName, String businessNumber, String businessAddress, String address, String telNumber, String introduction, String openTime, String dayOff, String originCountry, String logoImageUrl, String backgroundImageUrl, Integer tableCount, String category, StoreStatus status) {
        this.seq = seq;
        this.ownerSeq = ownerSeq;
        this.name = name;
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

    public static GetStoreResponseDto of(StoreModel storeModel) {
        return GetStoreResponseDto.builder()
                .seq(storeModel.getSeq())
                .ownerSeq(storeModel.getOwnerSeq())
                .name(storeModel.getName())
                .businessName(storeModel.getBusinessName())
                .businessPersonName(storeModel.getBusinessPersonName())
                .businessNumber(storeModel.getBusinessNumber())
                .businessAddress(storeModel.getBusinessAddress())
                .address(storeModel.getAddress())
                .telNumber(storeModel.getTelNumber())
                .introduction(storeModel.getIntroduction())
                .openTime(storeModel.getOpenTime())
                .dayOff(storeModel.getDayOff())
                .originCountry(storeModel.getOriginCountry())
                .logoImageUrl(storeModel.getLogoImageUrl())
                .backgroundImageUrl(storeModel.getBackgroundImageUrl())
                .tableCount(storeModel.getTableCount())
                .category(storeModel.getCategory())
                .status(storeModel.getStatus())
                .build();
    }
}
