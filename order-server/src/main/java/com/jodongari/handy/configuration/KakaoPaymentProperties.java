package com.jodongari.handy.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties("kakao.payment")
public class KakaoPaymentProperties {
    String cid;
    String approveUrl;
    String cancelUrl;
    String failUrl;
}
