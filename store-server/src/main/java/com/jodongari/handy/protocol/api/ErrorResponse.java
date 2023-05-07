package com.jodongari.handy.protocol.api;

import lombok.Value;

@Value
public class ErrorResponse {
    String errorMessage;

    public ErrorResponse of(String message) {
        return new ErrorResponse(message);
    }
}
