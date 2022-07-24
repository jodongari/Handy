package com.jodongari.handy.controller;

import com.jodongari.handy.api.StoreApiUrl;
import com.jodongari.handy.protocol.ApiMessage;
import com.jodongari.handy.protocol.requestDto.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.responseDto.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterStoreResponseDto;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping(StoreApiUrl.MANAGE_TABLE_INFO)
    public ApiMessage<ManageTableInfoResponseDto> manageTableInfo(ManageTableInfoRequestDto request) {
        return ApiMessage.success(storeService.manageTableInfo(request));
    }
}
