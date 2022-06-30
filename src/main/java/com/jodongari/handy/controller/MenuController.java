package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.requestDto.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.responseDto.RegisterMenuResponseDto;
import com.jodongari.handy.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/api/menu/regist")
    public ResponseEntity<RegisterMenuResponseDto> registerMenu(RegisterMenuRequestDto request) {
        final RegisterMenuResponseDto response = menuService.registerMenu(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
