package com.jodongari.handy.service;

import com.jodongari.handy.protocol.requestDto.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.responseDto.RegisterMenuResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    public RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request) {
        return new RegisterMenuResponseDto(null);
    }
}
