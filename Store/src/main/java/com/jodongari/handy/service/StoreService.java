package com.jodongari.handy.service;

import com.jodongari.handy.protocol.requestDto.DeleteQRCodeRequestDto;
import com.jodongari.handy.protocol.requestDto.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.responseDto.DeleteQRCodeResponseDto;
import com.jodongari.handy.protocol.responseDto.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterStoreResponseDto;

public interface StoreService {

    ManageTableInfoResponseDto manageTableInfo(ManageTableInfoRequestDto request);

    RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request);
}
