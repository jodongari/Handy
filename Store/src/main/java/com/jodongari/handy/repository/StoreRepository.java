package com.jodongari.handy.repository;

import com.jodongari.handy.domain.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    @Query(value =  "SELECT * FROM user WHERE onwer_seq = :ownerSeq", nativeQuery = true)
    List<StoreEntity> findAllByOwnerSeq(Long ownerSeq);
}
