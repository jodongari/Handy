package com.jodongari.handy.protocol.session;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("kakaoPayments")
public class KakaoPaymentSession {
    @Id
    String partnerOrderId;
    String tid;
    String partnerUserId;

    @Builder
    public KakaoPaymentSession(String partnerOrderId, String tid, String partnerUserId) {
        this.partnerOrderId = partnerOrderId;
        this.tid = tid;
        this.partnerUserId = partnerUserId;
    }
}
