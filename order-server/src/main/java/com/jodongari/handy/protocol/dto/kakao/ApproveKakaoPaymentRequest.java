package com.jodongari.handy.protocol.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApproveKakaoPaymentRequest {
    String cid;
    String tid;
    String partnerOrderId;
    String partnerUserId;
    String pgToken;

    @Builder
    public ApproveKakaoPaymentRequest(String cid, String tid, String partnerOrderId, String partnerUserId, String pgToken) {
        this.cid = cid;
        this.tid = tid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.pgToken = pgToken;
    }
}
