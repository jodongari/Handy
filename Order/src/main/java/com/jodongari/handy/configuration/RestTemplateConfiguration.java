package com.jodongari.handy.configuration;

import com.jodongari.handy.protocol.exception.KakaoRestTemplateErrorHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfiguration {

    private static final int CONNECTION_TIMEOUT = 3 * 1000;
    private static final int READ_TIMEOUT = 3 * 1000;

    @Bean
    public HttpComponentsClientHttpRequestFactory restTemplateFactory(){
        final HttpComponentsClientHttpRequestFactory factory
                = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(CONNECTION_TIMEOUT);
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setHttpClient(HttpClientBuilder.create().build());
        return factory;
    }

    @Bean
    public RestTemplate kakaoRestTemplate(HttpComponentsClientHttpRequestFactory factory) {
        return new RestTemplateBuilder()
                .requestFactory(() -> factory)
                .errorHandler(new KakaoRestTemplateErrorHandler())
                .build();
    }
}
