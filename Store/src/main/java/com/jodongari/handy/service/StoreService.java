package com.jodongari.handy.service;

import com.jodongari.handy.protocol.requestDto.DeleteQRCodeRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterQRCodeRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.responseDto.DeleteQRCodeResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterQRCodeResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterStoreResponseDto;

public interface StoreService {
    RegisterQRCodeResponseDto registerQRCode(RegisterQRCodeRequestDto request);
    DeleteQRCodeResponseDto deleteQRCode(DeleteQRCodeRequestDto request);
    RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request);
}
