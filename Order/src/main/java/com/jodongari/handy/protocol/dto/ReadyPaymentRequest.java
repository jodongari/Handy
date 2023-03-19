package com.jodongari.handy.protocol.dto;

import com.jodongari.handy.protocol.model.MenuModel;
import lombok.Data;

import java.util.List;

@Data
public class ReadyPaymentRequest {
    Long userSeq;
    Long storeSeq;
    String tableName;
    List<MenuModel> menus;
}
