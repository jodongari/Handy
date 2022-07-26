package com.jodongari.handy.repository;

import com.google.common.hash.Hashing;
import com.jodongari.handy.domain.entity.QREntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QRCodeRepositoryTest {

    @Autowired
    private  QRCodeRepository qrCodeRepository;

    private final Long storeSeq = 1L;

    private String hashCode;
    private QREntity qrEntity;
    private List<QREntity> testTableInfoFromDB;

    @BeforeEach
    void registerTest() {
        String[] testTableNames = new String[]{"조인성", "박보영", "한지민"};

        for(String testTableName : testTableNames) {
            qrCodeRepository.save(QREntity.builder()
                    .hash(generateQRCode())
                    .storeSeq(storeSeq)
                    .tableName(testTableName)
                    .build());
        }

        testTableInfoFromDB = qrCodeRepository.findAll();
        hashCode = testTableInfoFromDB.get(0).getHash();
        qrEntity = qrCodeRepository.findById(hashCode).orElse(null);
    }

    @Test
    @DisplayName("테이블명 수정 확인")
    public void updateTest() {
        qrEntity.updateTableName("카리나");
        QREntity expected = qrCodeRepository.findById(hashCode).orElse(null);

        assertEquals("카리나", expected.getTableName());
    }

    @Test
    @DisplayName("테이블 정보 삭제")
    public void deleteTest() {
        qrCodeRepository.deleteById(hashCode);
        QREntity qrEntity = qrCodeRepository.findById(hashCode).orElse(null);
        assertEquals(null, qrEntity);
    }

    // TODO : 추후 generateQRCode를 Util Package에 넣어 Bean으로 관리할 예정
    private static String generateQRCode() {
        final String qrHash = Hashing.sha256()
                .hashString(String.valueOf(System.nanoTime()), StandardCharsets.UTF_8)
                .toString();
        return qrHash;
    }
}