package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.store.Store;
import com.jodongari.handy.infrastructure.repository.StoreRepository;
import com.jodongari.handy.protocol.dto.model.StoreModel;
import com.jodongari.handy.protocol.dto.request.GetStoresRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreResponseDto;
import com.jodongari.handy.protocol.dto.response.ManageTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;
import com.jodongari.handy.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public ManageTableInfoResponseDto manageTableInfo(ManageTableInfoRequestDto request) {
        return null;
    }

    @Override
    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request) {
        final StoreModel storeModel = request.toModel();

        final Store store = Store.create(storeModel);
        final Store result = storeRepository.save(store);

        return new RegisterStoreResponseDto(result);
    }

    @Override
    public List<GetStoreResponseDto> getStores(GetStoresRequestDto request) {
        final List<Store> result = storeRepository.findAllByOwnerSeq(request.getOwnerSeq());

        final List<StoreModel> storeModels = result.stream().map(Store::toModel).collect(Collectors.toList());
        final List<GetStoreResponseDto> response = storeModels.stream().map(GetStoreResponseDto::of).collect(Collectors.toList());

        return response;
    }


//    @Transactional
//    public ManageTableInfoResponseDto manageTableInfo(ManageTableInfoRequestDto request) {
//        final Long storeSeq = request.getStoreSeq();
//        final Map<String, String> tableInfos = request.getTableInfos();
//
//        final List<QRCode> tableInfoFromDB = qrCodeRepository.findAllByStoreSeq(storeSeq);
//        final Map<String, String> addTableMap = makeAddTableMap(tableInfoFromDB, tableInfos);
//        final Map<String, String> deleteTableMap = makeDeleteTableMap(tableInfoFromDB, tableInfos);
//        final Map<String, String> updateTableMap = makeUpdateTableMap(tableInfoFromDB, tableInfos);
//
//        for (Map.Entry<String,String> entry : addTableMap.entrySet()) {
//            String qrHash = entry.getKey();
//            String tableName = entry.getValue();
//
//            qrCodeRepository.save(QRCode.builder()
//                            .hash(qrHash)
//                            .storeSeq(storeSeq)
//                            .tableName(tableName)
//                            .build());
//        }
//
//        for (Map.Entry<String,String> entry : deleteTableMap.entrySet()) {
//            String qrHash = entry.getKey();
//            qrCodeRepository.deleteById(qrHash);
//        }
//
//        for(QRCode entity : tableInfoFromDB) {
//            if (updateTableMap.containsKey(entity.getHash())) {
//                entity.updateTableName(updateTableMap.get(entity.getHash()));
//            }
//        }
//
//        return new ManageTableInfoResponseDto();
//    }
//
//    @Transactional
//    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request,
//                                                  MultipartFile storeImage,
//                                                  MultipartFile businessReportCardImage,
//                                                  MultipartFile businessLicenseImage,
//                                                  MultipartFile logoImage) throws Exception {
//        // TODO: Call the Business Number verification API
//
//        final File storeImageFile = new File(Objects.requireNonNull(storeImage.getOriginalFilename()));
//        final File businessReportCardImageFile = new File(Objects.requireNonNull(businessReportCardImage.getOriginalFilename()));
//        final File businessLicenseImageFile = new File(Objects.requireNonNull(businessLicenseImage.getOriginalFilename()));
//        final File logoImageFile = new File(Objects.requireNonNull(logoImage.getOriginalFilename()));
//        try {
//            storeImage.transferTo(storeImageFile);
//            businessReportCardImage.transferTo(businessReportCardImageFile);
//            businessLicenseImage.transferTo(businessLicenseImageFile);
//            logoImage.transferTo(logoImageFile);
//        } catch (IOException e) {
//            log.error("Fail to save image. request = {}", request);
//            // TODO: Defind custom exception
//            throw new Exception();
//        }
//
//        final String storeImageKey = fileObjectStorageService.uploadObjectToS3(storeImageFile);
//        final String businessReportCardImageKey = fileObjectStorageService.uploadObjectToS3(businessReportCardImageFile);
//        final String businessLicenseImageKey = fileObjectStorageService.uploadObjectToS3(businessLicenseImageFile);
//        final String logoImageKey = fileObjectStorageService.uploadObjectToS3(logoImageFile);
//
//        final Store storeEntityResult = storeRepository.save(request.dtoToEntity(storeImageKey,
//                businessReportCardImageKey, businessLicenseImageKey, logoImageKey));
//        final Map<String, String> initTableInfos = new HashMap<>();
//
//        for (int tableInfo = 1; tableInfo <= request.getTableCount(); ++tableInfo) {
//            final String tableInfoStr = String.valueOf(tableInfo);
//            initTableInfos.put(tableInfoStr, tableInfoStr);
//        }
//
//        final Map<String, String> addTableMap = makeAddTableMap(new ArrayList<>(), initTableInfos);
//
//        for (Map.Entry<String,String> entry : addTableMap.entrySet()) {
//            String qrHash = entry.getKey();
//            String tableName = entry.getValue();
//
//            qrCodeRepository.save(QRCode.builder()
//                    .hash(qrHash)
//                    .storeSeq(storeEntityResult.getSeq())
//                    .tableName(tableName)
//                    .build());
//        }
//
//        // TODO: 심사 이벤트 발행
//
//        return new RegisterStoreResponseDto(storeEntityResult);
//    }
//
//    @Override
//    public GetStoreResponse getStore(GetStoreRequest request) throws Exception {
//        final Long storeSeq = request.getSeq();
//        // TODO: throw custom exception
//        final Store entity = storeRepository.findById(storeSeq).orElseThrow(Exception::new);
//        final GetStoreResponse response =  new GetStoreResponse(new Store(entity));
//
//        return response;
//    }
//
//    private static String generateQRCode() {
//        final String qrHash = Hashing.sha256()
//                .hashString(String.valueOf(System.nanoTime()), StandardCharsets.UTF_8)
//                .toString();
//        return qrHash;
//    }
//
//    private static Map<String, String> makeAddTableMap(List<QRCode> tableInfoFromDB,
//                                                       Map<String, String> tableInfos){
//
//        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
//        final Map<String, String> addTableInfoMap = new HashMap<>();
//
//        for(Map.Entry<String,String> entry : tableInfos.entrySet()) {
//            String hash = entry.getKey();
//            String tableName = entry.getValue();
//
//            if(!db.containsKey(hash)){
//                addTableInfoMap.put(generateQRCode(), tableName);
//            }
//        }
//
//        return addTableInfoMap;
//    }

//    private static Map<String, String> makeUpdateTableMap(List<QRCode> tableInfoFromDB,
//                                                          Map<String, String> tableInfos) {
//        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
//        final Map<String, String> updateTableInfoMap = new HashMap<>();
//
//        for(Map.Entry<String,String> entry : tableInfos.entrySet()) {
//            String hash = entry.getKey();
//            String tableName = entry.getValue();
//
//            if(db.containsKey(hash)){
//                updateTableInfoMap.put(hash, tableName);
//            }
//        }
//
//        return updateTableInfoMap;
//    }
//
//    private static Map<String, String> makeDeleteTableMap(List<QRCode> tableInfoFromDB,
//                                                          Map<String, String> tableInfos){
//
//        final Map<String, String> db = qrEntityListToMap(tableInfoFromDB);
//        final Map<String, String> deleteTableInfoMap = new HashMap<>();
//
//        for(Map.Entry<String, String> entry : db.entrySet()){
//            String hash = entry.getKey();
//            String tableName = entry.getValue();
//
//            if(!tableInfos.containsKey(hash)){
//                deleteTableInfoMap.put(hash, tableName);
//            }
//        }
//
//        return deleteTableInfoMap;
//    }
//
//    private static Map<String, String> qrEntityListToMap(List<QRCode> entities) {
//        return entities.stream()
//                .collect(Collectors.toMap(QRCode::getHash, QRCode::getTableName));
//    }
}
