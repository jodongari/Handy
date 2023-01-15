package com.jodongari.handy.controller;

import com.jodongari.handy.file.FileObjectStorageService;
import com.jodongari.handy.protocol.url.ImageApiUrl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "이미지처리", description = "이미지처리 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageController {

    private final FileObjectStorageService fileObjectStorageService;

//    @Operation(summary = "이미지 등록")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetMenuResponseDto.class))),
//            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
//    })
    @PostMapping(value = ImageApiUrl.IMAGE_REGISTER)
    public String registerImage(@RequestPart(value = "file", required = false) MultipartFile imageFile) throws Exception {
        return fileObjectStorageService.uploadObjectToS3(imageFile);
    }

}
