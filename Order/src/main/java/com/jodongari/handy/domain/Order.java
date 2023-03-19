package com.jodongari.handy.domain;

import com.jodongari.handy.protocol.model.MenuModel;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
//    @MongoId
//    Long id;
    Long userSeq;
    Long storeSeq;
    String tableName;
    List<MenuModel> menus;
}
