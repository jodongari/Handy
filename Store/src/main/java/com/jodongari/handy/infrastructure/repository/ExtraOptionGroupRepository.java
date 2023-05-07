package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtraOptionGroupRepository extends JpaRepository<ExtraOptionGroup, Long> {
    List<ExtraOptionGroup> findAllByMenuSeq(Long menuSeq);
}
