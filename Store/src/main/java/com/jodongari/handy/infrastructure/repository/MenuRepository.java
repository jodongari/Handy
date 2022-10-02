package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.infrastructure.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    Optional<MenuEntity> findBySeq(Long storeSeq);

}
