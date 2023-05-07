package com.jodongari.handy.service;

import com.jodongari.handy.domain.Order;
import com.jodongari.handy.infrastructure.repository.redis.PaymentSessionRedisRepository;
import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.protocol.dto.kakao.ApproveKakaoPaymentResponse;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.protocol.model.MenuModel;
import com.jodongari.handy.protocol.model.MenuOptionModel;
import com.jodongari.handy.protocol.session.KakaoPaymentSession;
import com.jodongari.handy.service.impl.KakaoPaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KakaoPaymentServiceTest {
    @Mock
    private KakaoApiService kakaoApiService;

    @Mock
    private OrderDomainService orderDomainService;

    @Mock
    private SessionDomainService sessionDomainService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PaymentSessionRedisRepository paymentSessionRedisRepository;

    @InjectMocks
    private KakaoPaymentServiceImpl cut;

    private String partnerOrderId = "partnerOrderId";
    private Long partnerUserId = 1L;

    @Test
    public void readyKakaoPayment_success() {
        final MenuOptionModel menuOption = new MenuOptionModel();
        menuOption.setSeq(1L);
        menuOption.setName("소");
        menuOption.setPrice(1000);
        final MenuModel menu = new MenuModel();
        menu.setSeq(1L);
        menu.setCount(1);
        menu.setName("떡볶이");
        menu.setMenuOption(menuOption);
        final Order order = Order.builder()
                .orderSeq(partnerOrderId)
                .userSeq(partnerUserId)
                .storeSeq(1L)
                .menus(List.of(menu))
                .build();

        when(modelMapper.map(any(), any()))
                .thenReturn(order);
        when(kakaoApiService.callKakaoPaymentReady(any(), any(), any(), any()))
                .thenReturn(new ReadyKakaoPaymentResponse());

        cut.readyKakaoPayment(new ReadyPaymentRequest());
    }

    @Test
    public void approveKakaoPayment_success() {
        final Order order = Order.builder()
                .orderSeq(partnerOrderId)
                .userSeq(partnerUserId)
                .storeSeq(1L)
                .build();
        final KakaoPaymentSession kakaoPaymentSession = KakaoPaymentSession.builder()
                .tid("")
                .partnerUserId(Long.toString(partnerUserId))
                .partnerOrderId(partnerOrderId)
                .build();

        when(sessionDomainService.getSession(anyString(), eq(KakaoPaymentSession.class)))
                .thenReturn(kakaoPaymentSession);
        when(orderDomainService.registerOrder(any()))
                .thenReturn(order);
        when(kakaoApiService.callKakaoPaymentApprove(any(), any(), any(), any()))
                .thenReturn(new ApproveKakaoPaymentResponse());

        cut.approveKakaoPayment(new String(), new String());
    }
}
