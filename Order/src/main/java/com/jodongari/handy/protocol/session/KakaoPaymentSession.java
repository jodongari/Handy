package com.jodongari.handy.protocol.session;

import lombok.Builder;
import lombok.Data;

@Data
public class KakaoPaymentSession {
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
