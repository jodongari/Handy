package com.jodongari.handy.service;

import com.google.common.hash.Hashing;
import com.jodongari.handy.domain.entity.QREntity;
import com.jodongari.handy.protocol.requestDto.DeleteTableRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.requestDto.RegisterTableRequestDto;
import com.jodongari.handy.protocol.responseDto.DeleteTableResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterStoreResponseDto;
import com.jodongari.handy.protocol.responseDto.RegisterTableResponseDto;
import com.jodongari.handy.repository.QRCodeRepository;
import com.jodongari.handy.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final QRCodeRepository qrCodeRepository;

    private String generateQRCode() {
        final String qrHash = Hashing.sha256()
                .hashString(new Date().toString(), StandardCharsets.UTF_8)
                .toString();
        return qrHash;
    }

    @Transactional
    public RegisterTableResponseDto registerTable(RegisterTableRequestDto request) {
        Long storeSeq = request.getStoreSeq();

        for (Integer tableNumber : request.getTableNumber()) {
            final Optional<Boolean> count = qrCodeRepository.existByStoreSeqAndTableNumber(storeSeq, tableNumber);

            if(count.get()){
                continue;
            }

            String qrHash = this.generateQRCode();
            qrCodeRepository.save(QREntity.builder()
                            .hash(qrHash)
                            .storeSeq(storeSeq)
                            .tableNumber(tableNumber)
                            .build());
        }

        return new RegisterTableResponseDto();
    }

    @Transactional
    public DeleteTableResponseDto deleteTable(DeleteTableRequestDto request) {
        request.getHashes().stream()
                .forEach(hash -> qrCodeRepository.deleteById(hash));
        return new DeleteTableResponseDto();
    }

    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request) {
        return new RegisterStoreResponseDto("");
    }
}
