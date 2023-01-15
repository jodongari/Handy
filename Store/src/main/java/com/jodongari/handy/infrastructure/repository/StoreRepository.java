package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "SELECT * FROM store WHERE onwer_seq = :ownerSeq", nativeQuery = true)
    List<Store> findAllByOwnerSeq(Long ownerSeq);
}
