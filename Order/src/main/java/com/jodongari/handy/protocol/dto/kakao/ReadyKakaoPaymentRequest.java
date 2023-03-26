package com.jodongari.handy.protocol.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReadyKakaoPaymentRequest {

    String cid;
    String partnerOrderId;
    String partnerUserId;
    String itemName;
    Integer quantity;
    Integer totalAmount;
    Integer taxFreeAmount;
    Integer vatAmount;
    String approvalUrl;
    String cancelUrl;
    String failUrl;

    @Builder
    public ReadyKakaoPaymentRequest(String cid, String partnerOrderId, String partnerUserId, String itemName, Integer quantity, Integer totalAmount, Integer taxFreeAmount, Integer vatAmount, String approvalUrl, String cancelUrl, String failUrl) {
        this.cid = cid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.taxFreeAmount = taxFreeAmount;
        this.vatAmount = vatAmount;
        this.approvalUrl = approvalUrl;
        this.cancelUrl = cancelUrl;
        this.failUrl = failUrl;
    }
}
