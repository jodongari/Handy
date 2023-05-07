package com.jodongari.handy.protocol.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiMessage<T> {
    T result;
    String errorCode;
    String errorMessage;

    public static <T> ApiMessage<T> success(T result) {
        final ApiMessage<T> message = new ApiMessage<>();
        message.result = result;
        return message;
    }
}
