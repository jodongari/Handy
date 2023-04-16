package com.jodongari.handy.protocol.exception;

public class PaymentException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public PaymentException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
