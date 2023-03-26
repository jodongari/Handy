package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.Order;
import com.jodongari.handy.infrastructure.repository.KakaoPaymentRedisRepository;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.protocol.session.KakaoPaymentSession;
import com.jodongari.handy.service.KakaoApiService;
import com.jodongari.handy.service.OrderDomainService;
import com.jodongari.handy.service.KakaoPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class KakaoPaymentServiceImpl implements KakaoPaymentService {

    private final OrderDomainService orderDomainService;
    private final ModelMapper modelMapper;
    private final KakaoApiService kakaoApiService;
    private final KakaoPaymentRedisRepository kakaoPaymentRedisRepository;

    @Transactional(rollbackFor = Exception.class)
    public ReadyKakaoPaymentResponse readyKakaoPayment(ReadyPaymentRequest request) {

        final Order order = modelMapper.map(request, Order.class);
        final Order result = orderDomainService.registerOrder(order);

        final ReadyKakaoPaymentResponse response = kakaoApiService.callKakaoPaymentReady(result.getOrderSeq().toString(), result.getUserSeq().toString(), "메뉴1번 외 몇개", 1, 100, 0, 0);

        final KakaoPaymentSession session = KakaoPaymentSession.builder()
                .partnerOrderId(result.getOrderSeq().toString())
                .tid(response.getTid())
                .partnerUserId(result.getUserSeq().toString())
                .build();

        kakaoPaymentRedisRepository.save(session);

        return response;
    }

    public void approveKakaoPayment(String partnerOrderId, String pgToken) {

        KakaoPaymentSession kakaoPaymentSession = null;
        try {
            kakaoPaymentSession = kakaoPaymentRedisRepository.findById(partnerOrderId).orElseThrow(Exception::new);
        } catch(Exception e) {

        }

        kakaoApiService.callKakaoPaymentApprove(kakaoPaymentSession.getTid(), partnerOrderId, kakaoPaymentSession.getPartnerUserId(), pgToken);
    }
}
