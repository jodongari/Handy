package com.jodongari.handy.protocol.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApproveKakaoPaymentResponse {
    String aid;
    String tid;
    String cid;
    String partnerOrderId;
    String partnerUserId;
    String paymentMethodType;
    String itemName;
    Integer quantity;
    KakaoAmount amount;
    KakaoCardInfo cardInfo;
    String createdAt;
    String approvedAt;
}
