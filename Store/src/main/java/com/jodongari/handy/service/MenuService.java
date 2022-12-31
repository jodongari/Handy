package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.ManageMenuRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;

import java.util.List;

public interface MenuService {
//    RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request, MultipartFile imageFile) throws Exception;
    void registerMenu(RegisterMenuRequestDto request) throws Exception;
    List<GetMenuResponseDto> getMenu(Long storeSeq) throws Exception;
    void manageMenu(ManageMenuRequestDto request);
}
