package com.jodongari.handy.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties("kakao")
public class KakaoProperties {
    String appKey;
    String baseUrl;
}
