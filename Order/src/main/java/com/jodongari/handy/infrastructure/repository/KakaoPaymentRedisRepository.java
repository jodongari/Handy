package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.protocol.session.KakaoPaymentSession;
import org.springframework.data.repository.CrudRepository;

public interface KakaoPaymentRedisRepository extends CrudRepository<KakaoPaymentSession, String> {
}
