package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.api.ErrorResponse;
import com.jodongari.handy.protocol.dto.request.GetMenuRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;
import com.jodongari.handy.protocol.url.MenuApiUrl;
import com.jodongari.handy.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "메뉴", description = "메뉴 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetMenuResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = MenuApiUrl.MENU_GET)
    public GetMenuResponseDto getMenu(GetMenuRequestDto request) throws Exception {
        return menuService.getMenu(request.getStoreSeq());
    }

    //TODO - 22.10.22 이미지 저장 프로세스 정해지면 추가수정 예정
//    @PostMapping(value = MenuApiUrl.MENU_REGISTER, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ApiMessage<RegisterMenuResponseDto> registerMenu(@RequestPart final RegisterMenuRequestDto request,
//                                                            @RequestPart final MultipartFile imageFile) throws Exception {
//        return ApiMessage.success(menuService.registerMenu(request, imageFile));
//    }

    @Operation(summary = "메뉴 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterMenuResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = MenuApiUrl.MENU_REGISTER)
    public RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request) throws Exception {
        return menuService.registerMenu(request);
    }
}
