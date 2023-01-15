package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.DeleteStoreRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.GetStoreResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;

import java.util.List;

public interface StoreService {

    RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request);

    GetStoreResponseDto getStore(Long storeSeq);

    List<GetStoreResponseDto> getStores(Long ownerSeq);

    List<GetStoreInfoResponseDto> getStoreInfos(Long ownerSeq);

    void updateStore(UpdateStoreRequestDto request);

    void deleteStore(DeleteStoreRequestDto request);
}
