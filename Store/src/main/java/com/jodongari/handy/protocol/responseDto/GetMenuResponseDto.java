package com.jodongari.handy.protocol.responseDto;

import com.jodongari.handy.protocol.model.menu.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMenuResponseDto {

    private Menu menu;

    @Builder
    public GetMenuResponseDto(Menu menu) {
        this.menu = menu;
    }
}
