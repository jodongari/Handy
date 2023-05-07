package com.jodongari.handy.infrastructure.repository.redis;

import com.jodongari.handy.domain.PaymentSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSessionRedisRepository extends CrudRepository<PaymentSession, String> {
}
