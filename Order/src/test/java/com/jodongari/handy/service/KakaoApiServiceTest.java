package com.jodongari.handy.service;

import com.jodongari.handy.configuration.KakaoPaymentProperties;
import com.jodongari.handy.configuration.KakaoProperties;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.protocol.exception.ErrorCode;
import com.jodongari.handy.protocol.exception.PaymentException;
import com.jodongari.handy.service.impl.KakaoApiServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class KakaoApiServiceTest {

    private static String appKey = "appKey";
    private static String baseUrl = "baseUrl";
    private static String cid = "cid";
    private static String approveUrl = "approveUrl";
    private static String cancelUrl = "cancelUrl";
    private static String failUrl = "failUrl";
    private String partnerOrderId = "partnerOrderId";
    private String partnerUserId = "partnerUserId";
    private String itemName = "itemName";
    private Integer quantity = 1;
    private Integer totalAmount = 10000000;
    private Integer taxFreeAmount = 0;
    private Integer vatAmount = 0;

    private KakaoProperties kakaoProperties = mock(KakaoProperties.class);

    private KakaoPaymentProperties kakaoPaymentProperties = mock(KakaoPaymentProperties.class);

    private RestTemplate kakaoRestTemplate = mock(RestTemplate.class);

    @InjectMocks
    private KakaoApiServiceImpl kakaoApiServiceImpl;

    @BeforeAll
    public void setUp() {
        when(kakaoProperties.getAppKey()).thenReturn(appKey);
        when(kakaoProperties.getBaseUrl()).thenReturn(baseUrl);
        when(kakaoPaymentProperties.getCid()).thenReturn(cid);
        when(kakaoPaymentProperties.getApproveUrl()).thenReturn(approveUrl);
        when(kakaoPaymentProperties.getCancelUrl()).thenReturn(cancelUrl);
        when(kakaoPaymentProperties.getFailUrl()).thenReturn(failUrl);
    }

    @Test
    public void callKakaoPaymentReady_success() {
        final String tid = "tid";
        final String nextRedirectAppUrl = "nextReirectAppUrl";
        final String nextRedirectMobileUrl = "nexRedirectMobileUrl";
        final String nextRedirectPcUrl = "nextRedrectPcUrl";
        final String androidAppScheme = "androidApScheme";
        final String iosAppScheme = "iosAppScheme";
        final String createdAt = "createdAt";
        final ReadyKakaoPaymentResponse response = new ReadyKakaoPaymentResponse();
        response.setTid(tid);
        response.setNextRedirectAppUrl(nextRedirectAppUrl);
        response.setNextRedirectMobileUrl(nextRedirectMobileUrl);
        response.setNextRedirectPcUrl(nextRedirectPcUrl);
        response.setAndroidAppScheme(androidAppScheme);
        response.setIosAppScheme(iosAppScheme);
        response.setCreatedAt(createdAt);

        when(kakaoRestTemplate.postForEntity(anyString(), any(), eq(ReadyKakaoPaymentResponse.class)))
            .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        final ReadyKakaoPaymentResponse result = kakaoApiServiceImpl.callKakaoPaymentReady(partnerOrderId, partnerUserId,
                itemName, quantity, totalAmount, taxFreeAmount, vatAmount);

        assertEquals(response, result);
    }


    @Test
    public void callKakaoPaymentReady_fail() {
        when(kakaoRestTemplate.postForEntity(anyString(), any(), eq(ReadyKakaoPaymentResponse.class)))
                .thenThrow(new PaymentException(ErrorCode.EXTERNAL_BAD_REQUEST, "400 error"));

        assertThrows(PaymentException.class,() ->{
            kakaoApiServiceImpl.callKakaoPaymentReady(partnerOrderId, partnerUserId,
                    itemName, quantity, totalAmount, taxFreeAmount, vatAmount);
        });
    }
}
