package com.jodongari.handy.protocol.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoCardInfo {
    String interestFreeInstall;
    String bin;
    String cardType;
    String cardMid;
    String approvedId;
    String installMonth;
    String purchaseCorp;
    String purchaseCorpCode;
    String issuerCorp;
    String issuerCorpCode;
    String kakaopayPurchaseCorp;
    String kakaopayPurchaseCorpCode;
    String kakaopayIssuerCorp;
    String kakaopayIssuerCorpCode;
}
