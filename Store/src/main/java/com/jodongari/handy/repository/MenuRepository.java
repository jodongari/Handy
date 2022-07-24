package com.jodongari.handy.repository;

import com.jodongari.handy.domain.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    Optional<MenuEntity> findBySeq(Long storeSeq);

}
