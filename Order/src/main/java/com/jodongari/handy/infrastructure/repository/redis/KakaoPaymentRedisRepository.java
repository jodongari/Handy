package com.jodongari.handy.infrastructure.repository.redis;

import com.jodongari.handy.protocol.session.KakaoPaymentSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoPaymentRedisRepository extends CrudRepository<KakaoPaymentSession, String> {
}
