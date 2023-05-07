package com.jodongari.handy.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("paymentSession")
public class PaymentSession {
    @Id
    String key;
    String value;
}
