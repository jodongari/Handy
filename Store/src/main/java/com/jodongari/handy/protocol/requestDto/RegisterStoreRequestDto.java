package com.jodongari.handy.protocol.requestDto;

import com.jodongari.handy.entity.StoreEntity;
import com.jodongari.handy.entity.status.StoreStatus;
import lombok.Value;

@Value
public class RegisterStoreRequestDto {
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
    int tableCount;
    String category;
    StoreStatus storeStatus;

    public StoreEntity DtoToEntity(){
        return StoreEntity.builder().build();
    }

}
