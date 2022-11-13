package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.api.ErrorResponse;
import com.jodongari.handy.protocol.dto.request.DeleteStoreRequestDto;
import com.jodongari.handy.protocol.dto.request.GetStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreResponseDto;
import com.jodongari.handy.protocol.url.StoreApiUrl;
import com.jodongari.handy.protocol.dto.request.GetStoresRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;
import com.jodongari.handy.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "매장", description = "매장 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "매장 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterStoreResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(StoreApiUrl.STORE_REGISTER)
    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request) {
        return storeService.registerStore(request);
    }

    @Operation(summary = "매장 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetStoreResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(StoreApiUrl.STORE_GET)
    public GetStoreResponseDto getStore(GetStoreRequestDto request) {
        return storeService.getStore(request);
    }

    @Operation(summary = "매장 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetStoreResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(StoreApiUrl.STORES_GET)
    public List<GetStoreResponseDto> getStores(GetStoresRequestDto request) {
        return storeService.getStores(request);
    }

    @Operation(summary = "테이블 관리")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ManageTableInfoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(StoreApiUrl.MANAGE_TABLE_INFO)
    public void manageTableInfo(ManageTableInfoRequestDto request) {
        storeService.manageTableInfo(request);
    }

    @DeleteMapping(StoreApiUrl.STORE_DELETE)
    public void deleteStore(DeleteStoreRequestDto request) {
        storeService.deleteStore(request);
    }

}
