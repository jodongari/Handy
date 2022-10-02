package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.url.StoreApiUrl;
import com.jodongari.handy.protocol.api.ApiMessage;
import com.jodongari.handy.protocol.dto.request.GetStoreInfosRequest;
import com.jodongari.handy.protocol.dto.request.GetStoreRequest;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreInfosResponse;
import com.jodongari.handy.protocol.dto.response.GetStoreResponse;
import com.jodongari.handy.protocol.dto.response.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping(StoreApiUrl.STORE_REGISTER)
    public ApiMessage<RegisterStoreResponseDto> registerStore(@RequestPart("request") RegisterStoreRequestDto request,
                                                              @RequestPart("storeImage") MultipartFile storeImage,
                                                              @RequestPart("businessReportCardImage") MultipartFile businessReportCardImage,
                                                              @RequestPart("businessLicenseImage") MultipartFile businessLicenseImage,
                                                              @RequestPart("logoImage") MultipartFile logoImage) throws Exception {
        return ApiMessage.success(storeService.registerStore(request,
                                                             storeImage,
                                                             businessReportCardImage,
                                                             businessLicenseImage,
                                                             logoImage));
    }

    @GetMapping(StoreApiUrl.STORE_GET)
    public ApiMessage<GetStoreResponse> getStore(GetStoreRequest request) throws Exception {
        return ApiMessage.success(storeService.getStore(request));
    }

    @GetMapping(StoreApiUrl.STORE_INFO_GET)
    public ApiMessage<GetStoreInfosResponse> getStoreInfos(GetStoreInfosRequest request) {
        return ApiMessage.success(storeService.getStoreInfos(request));
    }

    @PostMapping(StoreApiUrl.MANAGE_TABLE_INFO)
    public ApiMessage<ManageTableInfoResponseDto> manageTableInfo(ManageTableInfoRequestDto request) {
        return ApiMessage.success(storeService.manageTableInfo(request));
    }
}
