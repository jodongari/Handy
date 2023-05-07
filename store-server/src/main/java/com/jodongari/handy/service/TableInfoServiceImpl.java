package com.jodongari.handy.service;

import com.jodongari.handy.api.protocol.dto.model.TableInfoModel;
import com.jodongari.handy.api.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.api.protocol.dto.response.GetTableInfoResponseDto;
import com.jodongari.handy.domain.tableInfo.TableInfo;
import com.jodongari.handy.infrastructure.repository.TableInfoRepository;
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
    private final TableInfoDomainService tableInfoDomainService;
    private final HashGeneratorService hashGeneratorService;
    private final ModelMapper modelMapper;

    @Override
    public List<GetTableInfoResponseDto> getTableInfos(Long storeSeq) {
        final List<TableInfo> results = tableInfoRepository.findByStoreSeq(storeSeq);
        return results.stream().map(result -> modelMapper.map(result, GetTableInfoResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void manageTableInfo(List<ManageTableInfoRequestDto> requests) {
        requests.forEach(request -> {
            TableInfo tableInfo;
            final TableInfoModel tableInfoModel = modelMapper.map(request, TableInfoModel.class);

            if(tableInfoDomainService.isNew(request.getSeq())) {
                String encryptedString = hashGeneratorService.encrypt();
                tableInfoModel.setTableHash(encryptedString);
                tableInfo = TableInfo.create(tableInfoModel);
            } else {
                tableInfo = TableInfo.merge(tableInfoModel);
            }

            tableInfoRepository.save(tableInfo);
        });
    }

}
