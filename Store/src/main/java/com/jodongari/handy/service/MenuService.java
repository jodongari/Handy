package com.jodongari.handy.service;

import com.jodongari.handy.protocol.MenuDTO;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface MenuService {
    RegisterMenuResponseDto registerMenu(MenuDTO request, MultipartFile imageFile);
    GetMenuResponseDto getMenu(Long storeSeq) throws Exception;
}
