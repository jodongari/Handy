package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.GetTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.response.GetTableInfoResponseDto;

import java.util.List;

public interface TableInfoService {

    List<GetTableInfoResponseDto> getTableInfos(GetTableInfoRequestDto request);
    void registerTableInfo(List<RegisterTableInfoRequestDto> request);
    void manageTableInfo(ManageTableInfoRequestDto request);
}
