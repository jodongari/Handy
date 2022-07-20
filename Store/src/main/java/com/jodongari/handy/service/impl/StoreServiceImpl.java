package com.jodongari.handy.service.impl;

import com.google.common.hash.Hashing;
import com.jodongari.handy.domain.entity.QREntity;
import com.jodongari.handy.protocol.requestDto.DeleteQRCodeRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.requestDto.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.responseDto.DeleteQRCodeResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterStoreResponseDto;
import com.jodongari.handy.protocol.responseDto.ManageTableInfoResponseDto;
import com.jodongari.handy.repository.QRCodeRepository;
import com.jodongari.handy.repository.StoreRepository;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final QRCodeRepository qrCodeRepository;

    @Transactional
    public ManageTableInfoResponseDto manageTableInfo(ManageTableInfoRequestDto request) {
        final Long storeSeq = request.getStoreSeq();
        final Map<String, String> tableInfos = request.getTableInfos();

        final List<QREntity> tableInfoFromDB = qrCodeRepository.findAllByStoreSeq(storeSeq);
        final Map<String, String> addTableList = makeAddTableList(tableInfoFromDB, tableInfos);
        final Map<String, String> deleteTableList = makeDeleteTableList(tableInfoFromDB, tableInfos);
        final Map<String, String> updateTableList = makeUpdateTableList(tableInfoFromDB, tableInfos);

        // 1. 추가
        for (Map.Entry<String,String> entry : addTableList.entrySet()) {
            String qrHash = entry.getKey();
            String tableName = entry.getValue();

            qrCodeRepository.save(QREntity.builder()
                            .hash(qrHash)
                            .storeSeq(storeSeq)
                            .tableName(tableName)
                            .build());
        }

        // 2. 삭제
        for (Map.Entry<String,String> entry : deleteTableList.entrySet()) {
            String qrHash = entry.getKey();
            qrCodeRepository.deleteById(qrHash);
        }

        // 3. 수정
        for(QREntity entity : tableInfoFromDB) {
            if (updateTableList.containsKey(entity.getHash())) {
                entity.updateTableName(updateTableList.get(entity.getHash()));
            }
        }

        return new ManageTableInfoResponseDto();
    }

    @Override
    public DeleteQRCodeResponseDto deleteQRCode(DeleteQRCodeRequestDto request) {
        return null;
    }

    @Transactional
    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request) {
        return new RegisterStoreResponseDto("");
    }

    private static String generateQRCode() {
        final String qrHash = Hashing.sha256()
                .hashString(new Date().toString(), StandardCharsets.UTF_8)
                .toString();
        return qrHash;
    }

    private static Map<String, String> makeAddTableList(List<QREntity> tableInfoFromDB,
                                                        Map<String, String> tableInfos){

        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);

        final Map<String, String> addTableInfoList = new HashMap<>();

        for(Map.Entry<String,String> entry : tableInfos.entrySet()) {
            String hash = entry.getKey();
            String tableName = entry.getValue();

            if(!db.containsKey(hash)){
                addTableInfoList.put(generateQRCode(), tableName);
            }
        }

        return addTableInfoList;
    }

    private static Map<String, String> makeUpdateTableList(List<QREntity> tableInfoFromDB,
                                                           Map<String, String> tableInfos) {
        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
        final Map<String, String> updateTableInfoList = new HashMap<>();

        for(Map.Entry<String,String> entry : tableInfos.entrySet()) {
            String hash = entry.getKey();
            String tableName = entry.getValue();

            if(db.containsKey(hash)){
                updateTableInfoList.put(hash, tableName);
            }
        }

        return updateTableInfoList;
    }

    private static Map<String, String> makeDeleteTableList(List<QREntity> tableInfoFromDB,
                                                           Map<String, String> tableInfos){

        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
        final Map<String, String> deleteTableInfoList = new HashMap<>();

        for(Map.Entry<String, String> entry : db.entrySet()){
            String hash = entry.getKey();
            String tableName = entry.getValue();

            if(!tableInfos.containsKey(hash)){
                deleteTableInfoList.put(hash, tableName);
            }
        }

        return deleteTableInfoList;
    }

    private static Map<String, String> qrEntityListToMap(List<QREntity> entities) {
        return entities.stream()
                .collect(Collectors.toMap(QREntity::getHash, QREntity::getTableName));
    }
}
