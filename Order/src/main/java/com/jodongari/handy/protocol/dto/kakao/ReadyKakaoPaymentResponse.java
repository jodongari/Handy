package com.jodongari.handy.protocol.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReadyKakaoPaymentResponse {
    String tid;
    String nextRedirectAppUrl;
    String nextRedirectMobileUrl;
    String nextRedirectPcUrl;
    String androidAppScheme;
    String iosAppScheme;
    String createdAt;
}
