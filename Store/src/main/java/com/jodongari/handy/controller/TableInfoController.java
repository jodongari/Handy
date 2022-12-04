package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.dto.request.DeleteTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.GetTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoNameRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateTableInfoStatusRequestDto;
import com.jodongari.handy.protocol.dto.response.GetTableInfoResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterTableInfoResponseDto;
import com.jodongari.handy.protocol.url.TableInfoApiUrl;
import com.jodongari.handy.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TableInfoController {

    private final TableInfoService tableInfoService;

    @GetMapping(TableInfoApiUrl.TABLE_INFO_GET)
    public List<GetTableInfoResponseDto> getTable(@RequestBody GetTableInfoRequestDto request) {
        return tableInfoService.getTableInfo(request);
    }

    @PostMapping(TableInfoApiUrl.TABLE_INFO_REGISTER)
    public RegisterTableInfoResponseDto registerTable(@RequestBody RegisterTableInfoRequestDto request) throws NoSuchAlgorithmException {
        return tableInfoService.registerTableInfo(request);
    }

    @PatchMapping(TableInfoApiUrl.TABLE_INFO_UPDATE_NAME)
    public void updateTableName(@RequestBody UpdateTableInfoNameRequestDto request) throws Exception {
        tableInfoService.updateTableName(request);
    }

    @PatchMapping(TableInfoApiUrl.TABLE_INFO_UPDATE_STATUS)
    public void updateTableStatus(@RequestBody UpdateTableInfoStatusRequestDto request) throws Exception {
        tableInfoService.updateTableStatus(request);
    }

    @DeleteMapping(TableInfoApiUrl.TABLE_INFO_DELETE)
    public void deleteTable(@RequestBody DeleteTableInfoRequestDto request) {
        tableInfoService.deleteTableInfo(request);
    }

    @PostMapping(TableInfoApiUrl.TABLE_INFO_MANAGE)
    public void manageTableInfo(@RequestBody ManageTableInfoRequestDto request) {
        tableInfoService.manageTableInfo(request);
    }
}
