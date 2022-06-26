package com.jodongari.handy.service;

import com.jodongari.handy.domain.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.domain.responseDto.RegisterStoreResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request) {
        return new RegisterStoreResponseDto();
    }
}
