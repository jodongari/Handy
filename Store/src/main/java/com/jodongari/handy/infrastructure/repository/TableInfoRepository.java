package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableInfoRepository extends JpaRepository<TableInfo, Long> {
    List<TableInfo> findByStoreSeq(Long storeSeq);
}
