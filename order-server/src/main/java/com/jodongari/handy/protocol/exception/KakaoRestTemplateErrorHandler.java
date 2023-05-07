package com.jodongari.handy.protocol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class KakaoRestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode().value() == 400) {
            throw new PaymentException(ErrorCode.EXTERNAL_BAD_REQUEST, "client error");
        } else if (httpResponse.getStatusCode().value() == 401) {
            throw new PaymentException(ErrorCode.NOT_AUTHORIZED, "server error");
        } else if (httpResponse.getStatusCode().value() == 500) {
            throw new PaymentException(ErrorCode.INTERNAL_SERVER_ERROR, "server error");
        } else {
            throw new PaymentException(ErrorCode.INTERNAL_SERVER_ERROR, "server error");
        }
    }
}
