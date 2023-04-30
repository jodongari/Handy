package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.Order;
import com.jodongari.handy.infrastructure.repository.mongo.OrderRepository;
import com.jodongari.handy.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class OrderDomainServiceImpl implements OrderDomainService {

    private final OrderRepository orderRepository;

    @Override
    public Order registerOrder(Order order) {
        order.setCreated(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
