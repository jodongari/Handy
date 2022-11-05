package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.dto.request.GetStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreResponseDto;
import com.jodongari.handy.protocol.url.StoreApiUrl;
import com.jodongari.handy.protocol.api.ApiMessage;
import com.jodongari.handy.protocol.dto.request.GetStoresRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping(StoreApiUrl.STORE_REGISTER)
    public ApiMessage<RegisterStoreResponseDto> registerStore(RegisterStoreRequestDto request) {
        return ApiMessage.success(storeService.registerStore(request));
    }

    @GetMapping(StoreApiUrl.STORE_GET)
    public ApiMessage<GetStoreResponseDto> getStore(GetStoreRequestDto request) {
        return ApiMessage.success(storeService.getStore(request));
    }

    @GetMapping(StoreApiUrl.STORES_GET)
    public ApiMessage<List<GetStoreResponseDto>> getStores(GetStoresRequestDto request) {
        return ApiMessage.success(storeService.getStores(request));
    }

    @PostMapping(StoreApiUrl.MANAGE_TABLE_INFO)
    public ApiMessage<ManageTableInfoResponseDto> manageTableInfo(ManageTableInfoRequestDto request) {
        return ApiMessage.success(storeService.manageTableInfo(request));
    }
}
