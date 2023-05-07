package com.jodongari.handy.service;

import com.jodongari.handy.api.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.api.protocol.dto.response.GetTableInfoResponseDto;

import java.util.List;

public interface TableInfoService {

    List<GetTableInfoResponseDto> getTableInfos(Long storeSeq);
    void manageTableInfo(List<ManageTableInfoRequestDto> requests);
}
