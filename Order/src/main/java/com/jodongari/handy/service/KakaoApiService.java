package com.jodongari.handy.service;


import com.jodongari.handy.protocol.dto.kakao.ApproveKakaoPaymentResponse;
import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;

public interface KakaoApiService {
    ReadyKakaoPaymentResponse callKakaoPaymentReady(String partnerOrderId,
                                                    String partnerUserId,
                                                    String itemName,
                                                    Integer quantity,
                                                    Integer totalAmount,
                                                    Integer taxFreeAmount,
                                                    Integer vatAmount);

    ApproveKakaoPaymentResponse callKakaoPaymentApprove(String tid,
                                                        String partnerOrderId,
                                                        String partnerUserId,
                                                        String pgToken);
}

