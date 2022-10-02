package com.jodongari.handy.protocol.dto.response;

import com.jodongari.handy.domain.menu.Menu;
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
