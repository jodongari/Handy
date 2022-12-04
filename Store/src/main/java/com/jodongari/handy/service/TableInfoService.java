package com.jodongari.handy.service;

import com.jodongari.handy.protocol.dto.request.DeleteTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.GetTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoNameRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoStatusRequestDto;
import com.jodongari.handy.protocol.dto.response.GetTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterTableInfoResponseDto;

import java.util.List;

public interface TableInfoService {

    List<GetTableInfoResponseDto> getTableInfo(GetTableInfoRequestDto request);
    RegisterTableInfoResponseDto registerTableInfo(RegisterTableInfoRequestDto request);
    void updateTableName(UpdateTableInfoNameRequestDto request) throws Exception;
    void updateTableStatus(UpdateTableInfoStatusRequestDto request) throws Exception;
    void deleteTableInfo(DeleteTableInfoRequestDto request);
    void manageTableInfo(ManageTableInfoRequestDto request);
}
