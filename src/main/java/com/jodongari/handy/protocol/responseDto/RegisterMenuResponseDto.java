package com.jodongari.handy.protocol.responseDto;

import com.jodongari.handy.protocol.menu.Menu;
import lombok.Builder;

public class RegisterMenuResponseDto {
    Menu menu;

    @Builder
    public RegisterMenuResponseDto(Menu menu) {
        this.menu = menu;
    }
}
