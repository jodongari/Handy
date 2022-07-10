package com.jodongari.handy.protocol;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiMessage<T> {
    T result;
    int errorCode;
    String errorMessage;

    @Builder
    public ApiMessage(T result, int errorCode, String errorMessage) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
