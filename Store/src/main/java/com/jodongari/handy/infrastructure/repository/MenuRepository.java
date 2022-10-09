package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findBySeq(Long storeSeq);

}
