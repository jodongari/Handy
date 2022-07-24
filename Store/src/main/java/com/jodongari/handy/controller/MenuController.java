package com.jodongari.handy.controller;

import com.jodongari.handy.api.MenuApiUrl;
import com.jodongari.handy.protocol.ApiMessage;
import com.jodongari.handy.protocol.requestDto.GetMenuRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.responseDto.GetMenuResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterMenuResponseDto;
import com.jodongari.handy.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping(value = MenuApiUrl.MENU_GET)
    public ApiMessage<GetMenuResponseDto> getMenu(final GetMenuRequestDto request) throws Exception {
        return ApiMessage.success(menuService.getMenu(request.getStoreSeq()));
    }

    @PostMapping(value = MenuApiUrl.MENU_REGISTER, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiMessage<RegisterMenuResponseDto> registerMenu(@RequestPart final RegisterMenuRequestDto request,
                                                            @RequestPart final MultipartFile imageFile) {
        return ApiMessage.success(menuService.registerMenu(request, imageFile));
    }

}
