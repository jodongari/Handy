package com.jodongari.handy.infrastructure.repository;

import com.jodongari.handy.domain.qrcode.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, String> {

    @Query(value = "SELECT hash" +
                        ", store_seq" +
                        ", table_name " +
                        "FROM QR " +
                        "WHERE store_seq = :storeSeq", nativeQuery = true)
    List<QRCode> findAllByStoreSeq(@Param("storeSeq") Long storeSeq);

    @Query(value = "SELECT EXISTS( SELECT qr.hash " +
                                    "FROM QR qr " +
                                   "WHERE 1 = 1" +
                                   "  AND qr.store_seq = :storeSeq" +
                                   "  And qr.table_name = :tableName" +
                                ")", nativeQuery = true)
    Optional<Boolean> existByStoreSeqAndTableName(@Param("storeSeq") Long storeSeq,
                                                    @Param("tableName") String tableName);
}
