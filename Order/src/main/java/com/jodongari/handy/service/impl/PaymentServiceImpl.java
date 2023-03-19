package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.Order;
import com.jodongari.handy.protocol.dto.ReadyPaymentRequest;
import com.jodongari.handy.protocol.dto.ReadyPaymentResponse;
import com.jodongari.handy.service.OrderDomainService;
import com.jodongari.handy.service.PaymentService;
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
public class PaymentServiceImpl implements PaymentService {

    private final OrderDomainService orderDomainService;
    private final ModelMapper modelMapper;

    public ReadyPaymentResponse readyPayment(ReadyPaymentRequest request) {
        // order mongodb에 저장
        final Order order = modelMapper.map(request, Order.class);
        final Order result = orderDomainService.registerOrder(order);
        log.info("{}", order);
        log.info("{}", result);
        //

        return new ReadyPaymentResponse();
    }
}
