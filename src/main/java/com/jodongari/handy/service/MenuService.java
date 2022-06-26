package com.jodongari.handy.service;

import com.jodongari.handy.domain.requestDto.RegisterMenuRequestDto;
import com.jodongari.handy.domain.responseDto.RegisterMenuResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    public RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request) {
        return new RegisterMenuResponseDto();
    }
}
