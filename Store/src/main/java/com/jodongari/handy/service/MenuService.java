package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface MenuService {
    RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request, MultipartFile imageFile) throws Exception;
    GetMenuResponseDto getMenu(Long storeSeq) throws Exception;
}
