package com.jodongari.handy.protocol.model;

import com.jodongari.handy.domain.entity.StoreEntity;
import com.jodongari.handy.domain.entity.status.StoreStatus;
import lombok.Value;

@Value
public class StoreInfo {
    Long seq;
    String name;
    String logo;
    String category;
    StoreStatus status;

    public StoreInfo(StoreEntity entity) {
        this.seq = entity.getSeq();
        this.name = entity.getName();
        this.logo = entity.getLogo();
        this.category = entity.getCategory();
        this.status = entity.getStatus();
    }
}
