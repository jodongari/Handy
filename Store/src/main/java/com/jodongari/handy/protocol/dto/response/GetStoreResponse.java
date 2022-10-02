package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.store.Store;
import lombok.Value;

@Value
public class GetStoreResponse {
    Store store;
}
