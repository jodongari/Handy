package com.jodongari.handy.protocol.responseDto;

import com.jodongari.handy.protocol.model.StoreInfo;
import lombok.Value;

import java.util.List;

@Value
public class GetStoreInfosResponse {
    List<StoreInfo> storeInfos;
}
