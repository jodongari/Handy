package com.jodongari.handy.protocol.exception;

public enum ErrorCode {
    BAD_REQUEST(400, 400),
    EXTERNAL_BAD_REQUEST(400, 1400),

    NOT_AUTHORIZED(401, 401),
    NOT_FOUND(404, 404),

    INTERNAL_SERVER_ERROR(500, 500),
    EXTERNAL_SERVER_ERROR(500, 1500);

    private final int statusCode;
    private final int errorCode;

    ErrorCode(int statusCode, int errorCode) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
