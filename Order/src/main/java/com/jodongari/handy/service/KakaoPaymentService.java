package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;

public interface KakaoPaymentService {

    ReadyKakaoPaymentResponse readyKakaoPayment(ReadyPaymentRequest request);

    void approveKakaoPayment(String partnerOrderId, String pgToken);
}
