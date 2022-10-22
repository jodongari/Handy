package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.api.ApiMessage;
import com.jodongari.handy.protocol.dto.request.GetMenuRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import com.jodongari.handy.protocol.url.MenuApiUrl;
import com.jodongari.handy.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping(value = MenuApiUrl.MENU_GET)
    public ApiMessage<GetMenuResponseDto> getMenu(final GetMenuRequestDto request) throws Exception {
        return ApiMessage.success(menuService.getMenu(request.getStoreSeq()));
    }

    //TODO - 22.10.22 이미지 저장 프로세스 정해지면 추가수정 예정
//    @PostMapping(value = MenuApiUrl.MENU_REGISTER, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ApiMessage<RegisterMenuResponseDto> registerMenu(@RequestPart final RegisterMenuRequestDto request,
//                                                            @RequestPart final MultipartFile imageFile) throws Exception {
//        return ApiMessage.success(menuService.registerMenu(request, imageFile));
//    }

    @PostMapping(value = MenuApiUrl.MENU_REGISTER)
    public ApiMessage<RegisterMenuResponseDto> registerMenu(@RequestPart final RegisterMenuRequestDto request) throws Exception {
        return ApiMessage.success(menuService.registerMenu(request));
    }

}
