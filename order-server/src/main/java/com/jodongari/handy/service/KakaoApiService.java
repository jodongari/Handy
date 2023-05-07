package com.jodongari.handy.service;


import com.jodongari.handy.protocol.dto.kakao.ApproveKakaoPaymentResponse;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;

public interface KakaoApiService {
    ReadyKakaoPaymentResponse callKakaoPaymentReady(String partnerOrderId,
                                                    String partnerUserId,
                                                    String itemName,
                                                    Integer totalAmount);

    ApproveKakaoPaymentResponse callKakaoPaymentApprove(String tid,
                                                        String partnerOrderId,
                                                        String partnerUserId,
                                                        String pgToken);
}

