package com.jodongari.handy.domain;

import com.jodongari.handy.protocol.model.MenuModel;
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
}
