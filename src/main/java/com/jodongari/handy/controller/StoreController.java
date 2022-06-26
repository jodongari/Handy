package com.jodongari.handy.controller;

import com.jodongari.handy.domain.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.domain.responseDto.RegisterStoreResponseDto;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/api/store/regist")
    public ResponseEntity<RegisterStoreResponseDto> registerStore(RegisterStoreRequestDto request) {
        final RegisterStoreResponseDto response = storeService.registerStore(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
