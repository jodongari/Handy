package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.protocol.dto.ReadyPaymentResponse;
import com.jodongari.handy.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay/kakao/ready")
    public ReadyPaymentResponse readyPayment(@RequestBody ReadyPaymentRequest request) {
        return paymentService.readyPayment(request);
    }
}
