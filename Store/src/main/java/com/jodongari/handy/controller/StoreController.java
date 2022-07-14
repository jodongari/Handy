package com.jodongari.handy.controller;

import com.jodongari.handy.api.StoreApiUrl;
import com.jodongari.handy.protocol.ApiMessage;
import com.jodongari.handy.protocol.requestDto.DeleteQRCodeRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterQRCodeRequestDto;
import com.jodongari.handy.protocol.responseDto.DeleteQRCodeResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterStoreResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterQRCodeResponseDto;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping(StoreApiUrl.STORE_REGISTER)
    public ApiMessage<RegisterStoreResponseDto> registerStore(RegisterStoreRequestDto request) {
        final RegisterStoreResponseDto response = storeService.registerStore(request);

        return ApiMessage.success(response);
    }

    @PostMapping(StoreApiUrl.QRCODE_REGISTER)
    public ApiMessage<RegisterQRCodeResponseDto> registerQRCode(RegisterQRCodeRequestDto request) {
        return ApiMessage.success(storeService.registerQRCode(request));
    }

    @DeleteMapping(StoreApiUrl.QRCODE_DELETE)
    public ApiMessage<DeleteQRCodeResponseDto> deleteQRCode(DeleteQRCodeRequestDto request) {
        return ApiMessage.success(storeService.deleteQRCode(request));
    }
}
