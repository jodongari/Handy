package com.jodongari.handy.protocol.responseDto;


import lombok.Builder;

public class RegisterStoreResponseDto {
    private String hash;

    @Builder
    public RegisterStoreResponseDto(String hash) {
        this.hash = hash;
    }
}
