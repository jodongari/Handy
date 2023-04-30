package com.jodongari.handy.infrastructure.repository.mongo;

import com.jodongari.handy.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
