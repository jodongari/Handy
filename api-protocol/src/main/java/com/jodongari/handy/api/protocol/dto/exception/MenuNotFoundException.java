package com.jodongari.handy.api.protocol.dto.exception;

public class MenuNotFoundException extends RuntimeException {

    public MenuNotFoundException(String message) {
        super(message);
    }
}
