package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.tableInfo.TableInfo;
import com.jodongari.handy.infrastructure.repository.TableInfoRepository;
import com.jodongari.handy.protocol.dto.model.TableInfoModel;
import com.jodongari.handy.protocol.dto.request.DeleteTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.GetTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoNameRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoStatusRequestDto;
import com.jodongari.handy.protocol.dto.response.GetTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterTableInfoResponseDto;
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

    public List<GetTableInfoResponseDto> getTableInfo(GetTableInfoRequestDto request) {
        final List<TableInfo> results = tableInfoRepository.findAllByStoreSeq(request.getStoreSeq());
        return results.stream().map(x -> modelMapper.map(x, GetTableInfoResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterTableInfoResponseDto registerTableInfo(RegisterTableInfoRequestDto request) {
        final TableInfoModel tableInfoModel = modelMapper.map(request, TableInfoModel.class);
        String encryptedString = hashGeneratorService.encrypt();
        tableInfoModel.setTableHash(encryptedString);

        final TableInfo result = tableInfoRepository.save(TableInfo.create(tableInfoModel));
        final TableInfoModel resultModel = result.toModel();

        return modelMapper.map(resultModel, RegisterTableInfoResponseDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTableName(UpdateTableInfoNameRequestDto request) throws Exception {
        final TableInfo result = tableInfoRepository.findById(request.getTableSeq()).orElseThrow(Exception::new);
        result.updateTableInfoName(request.getTableName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTableStatus(UpdateTableInfoStatusRequestDto request) throws Exception {
        final TableInfo result = tableInfoRepository.findById(request.getTableSeq()).orElseThrow(Exception::new);
        result.updateTableInfoStatus(request.getStatus());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTableInfo(DeleteTableInfoRequestDto request) {
        final TableInfoModel tableInfoModel = modelMapper.map(request, TableInfoModel.class);
        tableInfoRepository.deleteById(tableInfoModel.getSeq());
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

        deleteTableInfoModels
                .stream()
                .peek(deleteTableInfoModel -> {
                    final TableInfo tableInfo = tableInfoRepository.findById(deleteTableInfoModel.getSeq()).orElseThrow();
                    tableInfo.updateTableInfoStatus(TableInfo.TableInfoStatus.DELETE);
                });

        updateTableInfoModels
                .stream()
                .peek(updateTableInfoModel -> {
                    final TableInfo tableInfo = tableInfoRepository.findById(updateTableInfoModel.getSeq()).orElseThrow();
                    tableInfo.updateTableInfo(updateTableInfoModel.getTableName(), updateTableInfoModel.getStatus());
                });

        registerTableInfoModels
                .stream()
                .peek(registerTableInfoModel -> registerTableInfo(registerTableInfoModel));
    }

    private void registerTableInfo(TableInfoModel registerTableInfoModel) {
        String encryptedString = hashGeneratorService.encrypt();
        registerTableInfoModel.setTableHash(encryptedString);
        tableInfoRepository.save(TableInfo.create(registerTableInfoModel));
    }
}
