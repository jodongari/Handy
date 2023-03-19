package com.jodongari.handy.protocol.dto;

import lombok.Data;

@Data
public class ReadyPaymentResponse {
    String tid;
    String nextRedirectAppUrl;
    String nextRedirectMobileUrl;
    String nextRedirectPcUrl;
    String androidAppScheme;
    String iosAppScheme;
    String createdAt;
}
