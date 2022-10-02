package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class QRCodeScanResponseDto {
    private String hash;
    private String storeSeq;
    private Integer tableNumber;
    private String storeName;
    private String businessName;
    private String businessNumber;
    private String address;
    private String telNumber;
    private String introduction;
    private String originCountry;
    private String logoImageURL;
    private String backgroundImageURL;
    private List<Menu> menus;

    @Builder
    public QRCodeScanResponseDto(String hash, String storeSeq, Integer tableNumber, String storeName, String businessName, String businessNumber, String address, String telNumber, String introduction, String originCountry, String logoImageURL, String backgroundImageURL, List<Menu> menus) {
        this.hash = hash;
        this.storeSeq = storeSeq;
        this.tableNumber = tableNumber;
        this.storeName = storeName;
        this.businessName = businessName;
        this.businessNumber = businessNumber;
        this.address = address;
        this.telNumber = telNumber;
        this.introduction = introduction;
        this.originCountry = originCountry;
        this.logoImageURL = logoImageURL;
        this.backgroundImageURL = backgroundImageURL;
        this.menus = menus;
    }
}
