package com.jodongari.handy.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TableInfoRepositoryTest {

//
//    @Autowired
//    private TableInfoRepository qrCodeRepository;
//
//    private final Long storeSeq = 1L;
//
//    private String hashCode;
//    private TableInfo qrEntity;
//    private List<TableInfo> testTableInfoFromDB;
//
//    @BeforeEach
//    @Disabled
//    void registerTest() {
//        String[] testTableNames = new String[]{"조인성", "박보영", "한지민"};
//
//        for(String testTableName : testTableNames) {
//            qrCodeRepository.save(TableInfo.builder()
//                    .hash(generateQRCode())
//                    .storeSeq(storeSeq)
//                    .tableName(testTableName)
//                    .build());
//        }
//
//        testTableInfoFromDB = qrCodeRepository.findAll();
//        hashCode = testTableInfoFromDB.get(0).getHash();
//        qrEntity = qrCodeRepository.findById(hashCode).orElse(null);
//    }
//
//    @Test
//    @Disabled
//    @DisplayName("테이블명 수정 확인")
//    public void updateTest() {
//        qrEntity.updateTableName("카리나");
//        TableInfo expected = qrCodeRepository.findById(hashCode).orElse(null);
//
//        assertEquals("카리나", expected.getTableName());
//    }
//
//    @Test
//    @Disabled
//    @DisplayName("테이블 정보 삭제")
//    public void deleteTest() {
//        qrCodeRepository.deleteById(hashCode);
//        TableInfo qrEntity = qrCodeRepository.findById(hashCode).orElse(null);
//
//        assertEquals(null, qrEntity);
//    }
//
//    // TODO : 추후 generateQRCode를 Util Package에 넣어 Bean으로 관리할 예정
//    private static String generateQRCode() {
//        final String qrHash = Hashing.sha256()
//                .hashString(String.valueOf(System.nanoTime()), StandardCharsets.UTF_8)
//                .toString();
//        return qrHash;
//    }
}
