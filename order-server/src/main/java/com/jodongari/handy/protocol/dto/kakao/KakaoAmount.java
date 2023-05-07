package com.jodongari.handy.protocol.dto.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAmount {
    Integer total;
    Integer taxFree;
    Integer vat;
    Integer point;
    Integer discount;
    Integer greenDeposit;
}
