package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;

public interface MenuService {
//    RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request, MultipartFile imageFile) throws Exception;
    void registerMenu(RegisterMenuRequestDto request) throws Exception;
    GetMenuResponseDto getMenu(Long storeSeq) throws Exception;
}
