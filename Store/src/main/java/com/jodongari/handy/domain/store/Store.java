package com.jodongari.handy.domain.store;

import com.jodongari.handy.domain.store.vo.StoreStatus;
import com.jodongari.handy.protocol.dto.model.StoreModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "STORE")
public class Store {

    private static final int DEFAULT_TABLE_COUNT = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "OWNER_SEQ")
    private Long ownerSeq;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BUSINESS_REPORT_CARD_IMAGE_URL", nullable = false, length = 100)
    private String businessReportCardImageUrl;

    @Column(name = "BUSINESS_LICENSE_IMAGE_URL", nullable = false, length = 100)
    private String businessLicenseImageUrl;

    @Column(name = "BUSINESS_NAME", nullable = false, length = 100)
    private String businessName;

    @Column(name = "BUSINESS_PERSON_NAME", nullable = false, length = 30)
    private String businessPersonName;

    @Column(name = "BUSINESS_NUMBER", nullable = false, length = 10)
    private String businessNumber;

    @Column(name = "BUSINESS_ADDRESS", nullable = false, length = 200)
    private String businessAddress;

    @Column(name = "ADDRESS", nullable = false, length = 200)
    private String address;

    @Column(name = "TEL_NUMBER", nullable = false, length = 20)
    private String telNumber;

    @Column(name = "INTRODUCTION", nullable = false, length = 1000)
    private String introduction;

    @Column(name = "OPEN_TIME", nullable = false, length = 100)
    private String openTime;

    @Column(name = "DAY_OFF", nullable = false, length = 50)
    private String dayOff;

    @Column(name = "ORIGIN_COUNTRY", nullable = false, length = 400)
    private String originCountry;

    @Column(name = "LOGO_IMAGE_URL", nullable = false, length = 100)
    private String logoImageUrl;

    @Column(name = "BACKGROUND_IMAGE_URL", nullable = false, length = 100)
    private String backgroundImageUrl;

    @Column(name = "TABLE_COUNT", nullable = false)
    private Integer tableCount;

    @Column(name = "CATEGORY", nullable = false, length = 20)
    private String category;

    @Column(name = "STATUS", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Builder
    public Store(Long seq, Long ownerSeq, String name, String businessReportCardImageUrl, String businessLicenseImageUrl, String businessName, String businessPersonName, String businessNumber,
                 String businessAddress, String address, String telNumber, String introduction, String openTime, String dayOff, String originCountry, String logoImageUrl, String backgroundImageUrl, Integer tableCount, String category, StoreStatus status) {
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

    public static Store create(StoreModel storeModel) {
        return Store.builder()
                .seq(storeModel.getSeq())
                .ownerSeq(storeModel.getOwnerSeq())
                .name(storeModel.getName())
                .businessReportCardImageUrl(storeModel.getBusinessReportCardImageUrl())
                .businessLicenseImageUrl(storeModel.getBusinessLicenseImageUrl())
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
                .tableCount(DEFAULT_TABLE_COUNT)
                .category(storeModel.getCategory())
                .status(storeModel.getStatus())
                .build();
    }
}
