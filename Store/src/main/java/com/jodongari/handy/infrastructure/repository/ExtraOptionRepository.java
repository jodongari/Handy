package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.menu.ExtraOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtraOptionRepository extends JpaRepository<ExtraOption, Long> {
    List<ExtraOption> findAllByExtraOptionGroupSeq(Long extraOptionGroupSeq);
}
