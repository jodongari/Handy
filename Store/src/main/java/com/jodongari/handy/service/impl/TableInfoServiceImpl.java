package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import com.jodongari.handy.infrastructure.repository.TableInfoRepository;
import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import com.jodongari.handy.protocol.dto.request.GetTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.response.GetTableInfoResponseDto;
import com.jodongari.handy.service.HashGeneratorService;
import com.jodongari.handy.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class TableInfoServiceImpl implements TableInfoService {
    private final TableInfoRepository tableInfoRepository;
    private final HashGeneratorService hashGeneratorService;
    private final ModelMapper modelMapper;

    public List<GetTableInfoResponseDto> getTableInfos(GetTableInfoRequestDto request) {
        final List<TableInfo> results = tableInfoRepository.findAllByStoreSeq(request.getStoreSeq());
        return results.stream().map(x -> modelMapper.map(x, GetTableInfoResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerTableInfo(List<RegisterTableInfoRequestDto> request) {
        final List<TableInfoModel> tableInfoModels = request.stream()
                .map(tableInfo -> modelMapper.map(tableInfo, TableInfoModel.class))
                .collect(Collectors.toList());

        for(TableInfoModel tableInfoModel : tableInfoModels){
            registerTableInfo(tableInfoModel);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void manageTableInfo(ManageTableInfoRequestDto request) {
        final List<TableInfoModel> registerTableInfoModels = request.getRegisterTableInfosDto()
                .stream()
                .map(registerTableInfo -> modelMapper.map(registerTableInfo, TableInfoModel.class))
                .collect(Collectors.toList());

        final List<TableInfoModel> updateTableInfoModels = request.getUpdateTableInfosDto()
                .stream()
                .map(updateTableInfo -> modelMapper.map(updateTableInfo, TableInfoModel.class))
                .collect(Collectors.toList());

        final List<TableInfoModel> deleteTableInfoModels = request.getDeleteTableInfosDto()
                .stream()
                .map(deleteTableInfo -> modelMapper.map(deleteTableInfo, TableInfoModel.class))
                .collect(Collectors.toList());

        for(TableInfoModel deleteTableInfoModel : deleteTableInfoModels) {
            final TableInfo tableInfo = tableInfoRepository.findById(deleteTableInfoModel.getSeq()).orElseThrow();
            tableInfo.updateTableInfoStatus(TableInfo.TableInfoStatus.DELETE);
        }

        for(TableInfoModel updateTableInfoModel : updateTableInfoModels) {
            final TableInfo tableInfo = tableInfoRepository.findById(updateTableInfoModel.getSeq()).orElseThrow();
            tableInfo.updateTableInfo(updateTableInfoModel.getTableName(), updateTableInfoModel.getStatus());
        }

        for(TableInfoModel registerTableInfoModel : registerTableInfoModels) {
            registerTableInfo(registerTableInfoModel);
        }
    }

    private void registerTableInfo(TableInfoModel registerTableInfoModel) {
        String encryptedString = hashGeneratorService.encrypt();
        registerTableInfoModel.setTableHash(encryptedString);
        tableInfoRepository.save(TableInfo.create(registerTableInfoModel));
    }
}
