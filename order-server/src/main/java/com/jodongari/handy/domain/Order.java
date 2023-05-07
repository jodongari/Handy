package com.jodongari.handy.domain;

import com.jodongari.handy.protocol.model.MenuModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
@NoArgsConstructor
public class Order {
    @Id
    String orderSeq;
    Long userSeq;
    Long storeSeq;
    String tableName;
    List<MenuModel> menus;
    LocalDateTime created;
    @LastModifiedDate
    LocalDateTime updated;

    @Builder
    public Order(String orderSeq, Long userSeq, Long storeSeq, String tableName, List<MenuModel> menus) {
        this.orderSeq = orderSeq;
        this.userSeq = userSeq;
        this.storeSeq = storeSeq;
        this.tableName = tableName;
        this.menus = menus;
    }
}
