package com.jodongari.handy.protocol.dto.model;

import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.vo.ExtraOptionGroupStatus;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ExtraOptionGroupModel {

     Long seq;
     String name;
     String type;
     Integer minSelectLimit;
     Integer maxSelectLimit;
     ExtraOptionGroupStatus status; // TODO 확인 필요
     List<ExtraOptionModel> extraOptionModels;

     @Builder
     public ExtraOptionGroupModel(Long seq, String name, String type, Integer minSelectLimit, Integer maxSelectLimit, ExtraOptionGroupStatus status, List<ExtraOptionModel> extraOptionModels) {
          this.seq = seq;
          this.name = name;
          this.type = type;
          this.minSelectLimit = minSelectLimit;
          this.maxSelectLimit = maxSelectLimit;
          this.status = status;
          this.extraOptionModels = extraOptionModels;
     }

     public ExtraOptionGroup toEntity() {
          return ExtraOptionGroup.builder()
                  .name(this.name)
                  .type(this.type)
                  .minSelectLimit(this.minSelectLimit)
                  .maxSelectLimit(this.maxSelectLimit)
                  .status(this.status)
                  .extraOptions(extraOptionModels
                          .stream()
                          .map(ExtraOptionModel::toEntity)
                          .collect(Collectors.toList()))
                  .build();
     }
}
