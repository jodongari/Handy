package com.jodongari.handy.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jodongari.handy.configuration.KakaoPaymentProperties;
import com.jodongari.handy.configuration.KakaoProperties;
import com.jodongari.handy.protocol.dto.kakao.ApproveKakaoPaymentRequest;
import com.jodongari.handy.protocol.dto.kakao.ApproveKakaoPaymentResponse;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentRequest;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.service.KakaoApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({KakaoProperties.class, KakaoPaymentProperties.class})
@Slf4j
public class KakaoApiServiceImpl implements KakaoApiService {

    private final RestTemplate kakaoRestTemplate;
    private final KakaoProperties kakaoProperties;
    private final KakaoPaymentProperties kakaoPaymentProperties;
    private final ObjectMapper mapper = new ObjectMapper();

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer DEFAULT_TAX_FREE_AMOUNT = 0;
    private static final Integer DEFAULT_VAT_AMOUNT = 0;

    @Override
    public ReadyKakaoPaymentResponse callKakaoPaymentReady(String partnerOrderId, String partnerUserId, String itemName, Integer totalAmount) {

        ReadyKakaoPaymentRequest readyKakaoPaymentRequest = ReadyKakaoPaymentRequest.builder()
                .cid(kakaoPaymentProperties.getCid())
                .partnerOrderId(partnerOrderId)
                .partnerUserId(partnerUserId)
                .itemName(itemName)
                .quantity(DEFAULT_QUANTITY)
                .totalAmount(totalAmount)
                .taxFreeAmount(DEFAULT_TAX_FREE_AMOUNT)
                .vatAmount(DEFAULT_VAT_AMOUNT)
                .approvalUrl(kakaoPaymentProperties.getApproveUrl() + "?partner_order_id=" + partnerOrderId)
                .cancelUrl(kakaoPaymentProperties.getCancelUrl() + "?partner_order_id=" + partnerOrderId)
                .failUrl(kakaoPaymentProperties.getFailUrl() + "?partner_order_id=" + partnerOrderId)
                .build();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String,String> result = mapper.convertValue(readyKakaoPaymentRequest, new TypeReference<Map<String, String>>() {});
        parameters.setAll(result);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " +  kakaoProperties.getAppKey());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<ReadyKakaoPaymentResponse> responseEntity  = kakaoRestTemplate.postForEntity(
                kakaoProperties.getBaseUrl() + "v1/payment/ready",
                requestEntity,
                ReadyKakaoPaymentResponse.class);

        return responseEntity.getBody();
    }

    @Override
    public ApproveKakaoPaymentResponse callKakaoPaymentApprove(String tid, String partnerOrderId, String partnerUserId, String pgToken) {
        ApproveKakaoPaymentRequest approveKakaoPaymentRequest = ApproveKakaoPaymentRequest.builder()
                .cid(kakaoPaymentProperties.getCid())
                .tid(tid)
                .partnerOrderId(partnerOrderId)
                .partnerUserId(partnerUserId)
                .pgToken(pgToken)
                .build();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String,String> result = mapper.convertValue(approveKakaoPaymentRequest, new TypeReference<Map<String, String>>() {});
        parameters.setAll(result);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " +  kakaoProperties.getAppKey());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<ApproveKakaoPaymentResponse> responseEntity  = kakaoRestTemplate.postForEntity(
                kakaoProperties.getBaseUrl() + "v1/payment/approve",
                requestEntity,
                ApproveKakaoPaymentResponse.class);

        return responseEntity.getBody();
    }
}
