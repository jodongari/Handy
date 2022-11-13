package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.DeleteStoreRequestDto;
import com.jodongari.handy.protocol.dto.request.GetStoreRequestDto;
import com.jodongari.handy.protocol.dto.request.GetStoresRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreResponseDto;
import com.jodongari.handy.protocol.dto.response.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;

import java.util.List;

public interface StoreService {

    ManageTableInfoResponseDto manageTableInfo(ManageTableInfoRequestDto request);

    RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request);

    GetStoreResponseDto getStore(GetStoreRequestDto request);

    List<GetStoreResponseDto> getStores(GetStoresRequestDto request);

    void deleteStore(DeleteStoreRequestDto request);
}
