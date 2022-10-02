package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.menu.Menu;
import lombok.Builder;

public class RegisterMenuResponseDto {
    Menu menu;

    @Builder
    public RegisterMenuResponseDto(Menu menu) {
        this.menu = menu;
    }
}
