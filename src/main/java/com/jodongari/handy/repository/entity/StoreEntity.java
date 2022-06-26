package com.jodongari.handy.repository.entity;

import com.jodongari.handy.repository.entity.status.StoreStatus;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "STORE")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "OWNER_SEQ")
    private Long ownerSeq;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BUSINESS_REPORT_CARD_IMAGE", nullable = false, length = 100)
    private String businessReportCardImage;

    @Column(name = "BUSINESS_LICENSE_IMAGE", nullable = false, length = 100)
    private String businessLicenseImage;

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

    @Column(name = "LOGO", nullable = false, length = 100)
    private String logo;

    @Column(name = "BACKGROUND_IMAGE", nullable = false, length = 100)
    private String backgroundImage;

    @Column(name = "TABLE_COUNT", nullable = false)
    private Integer tableCount;

    @Column(name = "CATEGORY", nullable = false, length = 20)
    private String category;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Builder
    public StoreEntity(Long seq, String name, String businessReportCardImage, String businessLicenseImage, String businessName, String businessPersonName, String businessNumber,
                       String businessAddress, String address, String telNumber, String introduction, String openTime, String dayOff, String originCountry, String logo, String backgroundImage, Integer tableCount, String category, StoreStatus status) {
        this.seq = seq;
        this.name = name;
        this.businessReportCardImage = businessReportCardImage;
        this.businessLicenseImage = businessLicenseImage;
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
        this.logo = logo;
        this.backgroundImage = backgroundImage;
        this.tableCount = tableCount;
        this.category = category;
        this.status = status;
    }
}
