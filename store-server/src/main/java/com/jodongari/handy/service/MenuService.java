package com.jodongari.handy.service;

import com.jodongari.handy.api.protocol.dto.request.GetMenusByMenuSeqRequestDto;
import com.jodongari.handy.api.protocol.dto.request.ManageMenuRequestDto;
import com.jodongari.handy.api.protocol.dto.response.GetMenuResponseDto;

import java.util.List;

public interface MenuService {
    List<GetMenuResponseDto> getMenusByStore(Long storeSeq) throws Exception;
    List<GetMenuResponseDto> getMenusByMenuSeq(GetMenusByMenuSeqRequestDto request);
    void manageMenu(List<ManageMenuRequestDto> request);
}
