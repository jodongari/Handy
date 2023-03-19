package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.protocol.dto.ReadyPaymentResponse;

public interface PaymentService {

    ReadyPaymentResponse readyPayment(ReadyPaymentRequest request);
}
