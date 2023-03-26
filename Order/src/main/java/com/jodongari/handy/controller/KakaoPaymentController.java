package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.dto.kakao.ReadyKakaoPaymentResponse;
import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.service.KakaoPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class KakaoPaymentController {

    private final KakaoPaymentService kakaoPaymentService;

    @PostMapping("/pay/kakao/ready")
    public ReadyKakaoPaymentResponse readyKakaoPayment(@RequestBody ReadyPaymentRequest request) {
        return kakaoPaymentService.readyKakaoPayment(request);
    }

    @GetMapping("/pay/kakao/approve")
    public void approveKakaoPayment(@RequestParam("partner_order_id") String partnerOrderId,
                                    @RequestParam("pg_token") String pgToken) {
        kakaoPaymentService.approveKakaoPayment(partnerOrderId, pgToken);
    }

    @GetMapping("/pay/kakao/fail")
    public void failKakaoPayment() {
        log.info("fail");
    }

    @GetMapping("/pay/kakao/cancel")
    public void cancelKakaoPayment() {
        log.info("cancel");
    }
}
