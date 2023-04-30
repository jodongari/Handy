package com.jodongari.handy.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class KakaoPaymentServiceTest {
//    @Mock
//    private KakaoApiService kakaoApiService;
//
//    @Mock
//    private OrderDomainService orderDomainService;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private KakaoPaymentRedisRepository kakaoPaymentRedisRepository;
//
//    @InjectMocks
//    private KakaoPaymentServiceImpl cut;
//
//    private String partnerOrderId = "partnerOrderId";
//    private String partnerUserId = "partnerUserId";
//
//    @Test
//    public void readyKakaoPayment_success() {
//        final Order order = Order.builder()
//                .orderSeq(new ObjectId())
//                .userSeq(1L)
//                .storeSeq(1L)
//                .build();
//
//        when(modelMapper.map(any(), any()))
//                .thenReturn(order);
//        when(orderDomainService.registerOrder(any()))
//                .thenReturn(order);
//        when(kakaoApiService.callKakaoPaymentReady(any(), any(), any(), any(), any(), any(), any()))
//                .thenReturn(new ReadyKakaoPaymentResponse());
//
//        cut.readyKakaoPayment(new ReadyPaymentRequest());
//    }
}
