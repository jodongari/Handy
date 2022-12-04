package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.*;
import com.jodongari.handy.protocol.dto.response.GetStoreInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.GetStoreResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;

import java.util.List;

public interface StoreService {

    void manageTableInfo(ManageTableInfoRequestDto request);

    RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request);

    GetStoreResponseDto getStore(GetStoreRequestDto request);

    List<GetStoreResponseDto> getStores(GetStoresRequestDto request);

    List<GetStoreInfoResponseDto> getStoreInfos(GetStoreInfosRequestDto request);

    void updateStore(UpdateStoreRequestDto request);

    void deleteStore(DeleteStoreRequestDto request);
}
