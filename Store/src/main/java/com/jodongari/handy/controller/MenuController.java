package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.api.ApiMessage;
import com.jodongari.handy.protocol.dto.request.GetMenuRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import com.jodongari.handy.protocol.url.MenuApiUrl;
import com.jodongari.handy.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
                                                            @RequestPart final MultipartFile imageFile) throws Exception {
        return ApiMessage.success(menuService.registerMenu(request, imageFile));
    }

}
