package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.menu.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
    boolean existsBySeq(Long seq);

    List<MenuOption> findAllByMenuSeq(Long menuSeq);
}
