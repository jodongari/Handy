package com.jodongari.handy.configuration;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfiguration {

    private static final int CONNECTION_TIMEOUT = 3 * 1000;
    private static final int READ_TIMEOUT = 3 * 1000;

    @Bean
    public RestTemplate restTemplate(){
        final HttpComponentsClientHttpRequestFactory factory
                = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(CONNECTION_TIMEOUT);
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setHttpClient(HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }
}
