package com.jodongari.handy.service.impl;

import com.google.common.hash.Hashing;
import com.jodongari.handy.domain.entity.QREntity;
import com.jodongari.handy.domain.entity.StoreEntity;
import com.jodongari.handy.file.FileObjectStorageService;
import com.jodongari.handy.protocol.model.Store;
import com.jodongari.handy.protocol.model.StoreInfo;
import com.jodongari.handy.protocol.requestDto.GetStoreInfosRequest;
import com.jodongari.handy.protocol.requestDto.GetStoreRequest;
import com.jodongari.handy.protocol.requestDto.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.responseDto.GetStoreInfosResponse;
import com.jodongari.handy.protocol.responseDto.GetStoreResponse;
import com.jodongari.handy.protocol.responseDto.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterStoreResponseDto;
import com.jodongari.handy.repository.QRCodeRepository;
import com.jodongari.handy.repository.StoreRepository;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final FileObjectStorageService fileObjectStorageService;
    private final StoreRepository storeRepository;
    private final QRCodeRepository qrCodeRepository;

    @Transactional
    public ManageTableInfoResponseDto manageTableInfo(ManageTableInfoRequestDto request) {
        final Long storeSeq = request.getStoreSeq();
        final Map<String, String> tableInfos = request.getTableInfos();

        final List<QREntity> tableInfoFromDB = qrCodeRepository.findAllByStoreSeq(storeSeq);
        final Map<String, String> addTableMap = makeAddTableMap(tableInfoFromDB, tableInfos);
        final Map<String, String> deleteTableMap = makeDeleteTableMap(tableInfoFromDB, tableInfos);
        final Map<String, String> updateTableMap = makeUpdateTableMap(tableInfoFromDB, tableInfos);

        for (Map.Entry<String,String> entry : addTableMap.entrySet()) {
            String qrHash = entry.getKey();
            String tableName = entry.getValue();

            qrCodeRepository.save(QREntity.builder()
                            .hash(qrHash)
                            .storeSeq(storeSeq)
                            .tableName(tableName)
                            .build());
        }

        for (Map.Entry<String,String> entry : deleteTableMap.entrySet()) {
            String qrHash = entry.getKey();
            qrCodeRepository.deleteById(qrHash);
        }

        for(QREntity entity : tableInfoFromDB) {
            if (updateTableMap.containsKey(entity.getHash())) {
                entity.updateTableName(updateTableMap.get(entity.getHash()));
            }
        }

        return new ManageTableInfoResponseDto();
    }

    @Transactional
    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request,
                                                  MultipartFile storeImage,
                                                  MultipartFile businessReportCardImage,
                                                  MultipartFile businessLicenseImage,
                                                  MultipartFile logoImage) throws Exception {
        // TODO: Call the Business Number verification API

        final File storeImageFile = new File(Objects.requireNonNull(storeImage.getOriginalFilename()));
        final File businessReportCardImageFile = new File(Objects.requireNonNull(businessReportCardImage.getOriginalFilename()));
        final File businessLicenseImageFile = new File(Objects.requireNonNull(businessLicenseImage.getOriginalFilename()));
        final File logoImageFile = new File(Objects.requireNonNull(logoImage.getOriginalFilename()));
        try {
            storeImage.transferTo(storeImageFile);
            businessReportCardImage.transferTo(businessReportCardImageFile);
            businessLicenseImage.transferTo(businessLicenseImageFile);
            logoImage.transferTo(logoImageFile);
        } catch (IOException e) {
            log.error("Fail to save image. request = {}", request);
            // TODO: Defind custom exception
            throw new Exception();
        }

        final String storeImageKey = fileObjectStorageService.uploadObjectToS3(storeImageFile);
        final String businessReportCardImageKey = fileObjectStorageService.uploadObjectToS3(businessReportCardImageFile);
        final String businessLicenseImageKey = fileObjectStorageService.uploadObjectToS3(businessLicenseImageFile);
        final String logoImageKey = fileObjectStorageService.uploadObjectToS3(logoImageFile);

        final StoreEntity storeEntityResult = storeRepository.save(request.dtoToEntity(storeImageKey,
                businessReportCardImageKey, businessLicenseImageKey, logoImageKey));
        final Map<String, String> initTableInfos = new HashMap<>();

        for (int tableInfo = 1; tableInfo <= request.getTableCount(); ++tableInfo) {
            final String tableInfoStr = String.valueOf(tableInfo);
            initTableInfos.put(tableInfoStr, tableInfoStr);
        }

        final Map<String, String> addTableMap = makeAddTableMap(new ArrayList<>(), initTableInfos);

        for (Map.Entry<String,String> entry : addTableMap.entrySet()) {
            String qrHash = entry.getKey();
            String tableName = entry.getValue();

            qrCodeRepository.save(QREntity.builder()
                    .hash(qrHash)
                    .storeSeq(storeEntityResult.getSeq())
                    .tableName(tableName)
                    .build());
        }

        // TODO: 심사 이벤트 발행

        return new RegisterStoreResponseDto(storeEntityResult);
    }

    @Override
    public GetStoreResponse getStore(GetStoreRequest request) throws Exception {
        final Long storeSeq = request.getSeq();
        // TODO: throw custom exception
        final StoreEntity entity = storeRepository.findById(storeSeq).orElseThrow(Exception::new);
        final GetStoreResponse response =  new GetStoreResponse(new Store(entity));

        return response;
    }

    @Override
    public GetStoreInfosResponse getStoreInfos(GetStoreInfosRequest request) {
        final Long ownerSeq = request.getOwnerSeq();
        final List<StoreEntity> storeEntities = storeRepository.findAllByOwnerSeq(ownerSeq);
        final List<StoreInfo> storeInfos = new ArrayList<>();

        for (StoreEntity entity : storeEntities) {
            storeInfos.add(new StoreInfo(entity));
        }

        final GetStoreInfosResponse response = new GetStoreInfosResponse(storeInfos);

        return response;
    }

    private static String generateQRCode() {
        final String qrHash = Hashing.sha256()
                .hashString(new Date().toString(), StandardCharsets.UTF_8)
                .toString();
        return qrHash;
    }

    private static Map<String, String> makeAddTableMap(List<QREntity> tableInfoFromDB,
                                                       Map<String, String> tableInfos){

        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
        final Map<String, String> addTableInfoMap = new HashMap<>();

        for(Map.Entry<String,String> entry : tableInfos.entrySet()) {
            String hash = entry.getKey();
            String tableName = entry.getValue();

            if(!db.containsKey(hash)){
                addTableInfoMap.put(generateQRCode(), tableName);
            }
        }

        return addTableInfoMap;
    }

    private static Map<String, String> makeUpdateTableMap(List<QREntity> tableInfoFromDB,
                                                          Map<String, String> tableInfos) {
        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
        final Map<String, String> updateTableInfoMap = new HashMap<>();

        for(Map.Entry<String,String> entry : tableInfos.entrySet()) {
            String hash = entry.getKey();
            String tableName = entry.getValue();

            if(db.containsKey(hash)){
                updateTableInfoMap.put(hash, tableName);
            }
        }

        return updateTableInfoMap;
    }

    private static Map<String, String> makeDeleteTableMap(List<QREntity> tableInfoFromDB,
                                                          Map<String, String> tableInfos){

        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
        final Map<String, String> deleteTableInfoMap = new HashMap<>();

        for(Map.Entry<String, String> entry : db.entrySet()){
            String hash = entry.getKey();
            String tableName = entry.getValue();

            if(!tableInfos.containsKey(hash)){
                deleteTableInfoMap.put(hash, tableName);
            }
        }

        return deleteTableInfoMap;
    }

    private static Map<String, String> qrEntityListToMap(List<QREntity> entities) {
        return entities.stream()
                .collect(Collectors.toMap(QREntity::getHash, QREntity::getTableName));
    }
}
