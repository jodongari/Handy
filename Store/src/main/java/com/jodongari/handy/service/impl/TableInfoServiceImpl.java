package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import com.jodongari.handy.infrastructure.repository.TableInfoRepository;
import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import com.jodongari.handy.protocol.dto.request.DeleteTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.GetTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoNameRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoStatusRequestDto;
import com.jodongari.handy.protocol.dto.response.GetTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterTableInfoResponseDto;
import com.jodongari.handy.service.HashGeneratorService;
import com.jodongari.handy.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class TableInfoServiceImpl implements TableInfoService {
    private final TableInfoRepository tableInfoRepository;
    private final HashGeneratorService hashGeneratorService;

    public List<GetTableInfoResponseDto> getTableInfo(GetTableInfoRequestDto request) {
        final List<TableInfo> results = tableInfoRepository.findAllByStoreSeq(request.getStoreSeq());
        return results.stream().map(x -> x.toDto()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterTableInfoResponseDto registerTableInfo(RegisterTableInfoRequestDto request) throws NoSuchAlgorithmException {
        final TableInfoModel tableInfoModel = request.toModel();
        String encryptedString = hashGeneratorService.encrypt();
        tableInfoModel.setTableHash(encryptedString);

        final TableInfo result = tableInfoRepository.save(TableInfo.create(tableInfoModel));
        final TableInfoModel resultModel = result.toModel();

        return RegisterTableInfoResponseDto.of(resultModel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTableName(UpdateTableInfoNameRequestDto request) throws Exception {
        final TableInfo result = tableInfoRepository.findById(request.getTableSeq()).orElseThrow(Exception::new);
        result.updateTableName(request.getTableName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTableStatus(UpdateTableInfoStatusRequestDto request) throws Exception {
        final TableInfo result = tableInfoRepository.findById(request.getTableSeq()).orElseThrow(Exception::new);
        result.updateTableStatus(request.getStatus());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTableInfo(DeleteTableInfoRequestDto request) {
        final TableInfoModel tableInfoModel = request.toModel();
        tableInfoRepository.deleteById(tableInfoModel.getSeq());
    }
}
