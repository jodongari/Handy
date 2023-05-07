package com.jodongari.handy.service;

import com.google.common.hash.Hashing;
import com.jodongari.handy.domain.Order;
import com.jodongari.handy.infrastructure.repository.redis.PaymentSessionRedisRepository;
import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.service.impl.KakaoPaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KakaoPaymentServiceTest {
    @Mock
    private KakaoApiService kakaoApiService;

    @Mock
    private OrderDomainService orderDomainService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PaymentSessionRedisRepository paymentSessionRedisRepository;

    @InjectMocks
    private KakaoPaymentServiceImpl cut;

    private String partnerOrderId = "partnerOrderId";
    private String partnerUserId = "partnerUserId";

    @Test
    public void readyKakaoPayment_success() {
        final Order order = Order.builder()
                .orderSeq(Hashing.sha256().hashString(UUID.randomUUID().toString(), StandardCharsets.UTF_8).toString())
                .userSeq(1L)
                .storeSeq(1L)
                .build();

        when(modelMapper.map(any(), any()))
                .thenReturn(order);
        when(orderDomainService.registerOrder(any()))
                .thenReturn(order);
        when(kakaoApiService.callKakaoPaymentReady(any(), any(), any(), any()))
                .thenReturn(new ReadyKakaoPaymentResponse());

        cut.readyKakaoPayment(new ReadyPaymentRequest());
    }
}
