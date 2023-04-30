package com.jodongari.handy.protocol.session;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
