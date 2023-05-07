package com.jodongari.handy.api.protocol.dto.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuOptionModel {

    Long seq;
    String name;
    Integer price;
    String status; // TODO: 확인
    Long menuSeq;

    public void addMenuSeq(final long menuSeq) {
//        if (this.menuSeq == -1) {
            this.menuSeq = menuSeq;
//        }
    }

    @Builder
    public MenuOptionModel(Long seq, String name, Integer price, String status, Long menuSeq) {
        this.seq = seq;
        this.name = name;
        this.price = price;
        this.status = status;
        this.menuSeq = menuSeq;
    }
}
