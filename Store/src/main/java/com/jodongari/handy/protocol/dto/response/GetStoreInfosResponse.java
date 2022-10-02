package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.store.StoreInfo;
import lombok.Value;

import java.util.List;

@Value
public class GetStoreInfosResponse {
    List<StoreInfo> storeInfos;
}
