package com.jodongari.handy.domain;

import com.jodongari.handy.protocol.model.MenuModel;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    @MongoId
    ObjectId orderSeq;
    Long userSeq;
    Long storeSeq;
    String tableName;
    List<MenuModel> menus;

    @Builder
    public Order(ObjectId orderSeq, Long userSeq, Long storeSeq, String tableName, List<MenuModel> menus) {
        this.orderSeq = orderSeq;
        this.userSeq = userSeq;
        this.storeSeq = storeSeq;
        this.tableName = tableName;
        this.menus = menus;
    }
}
