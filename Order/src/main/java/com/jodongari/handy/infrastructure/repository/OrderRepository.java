package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
