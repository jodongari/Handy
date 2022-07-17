package com.jodongari.handy.service;

import com.jodongari.handy.protocol.requestDto.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.responseDto.RegisterMenuResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface MenuService {
    RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request, MultipartFile imageFile);

}
