package com.jodongari.handy.controller;

import com.jodongari.handy.protocol.dto.request.GetTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.ManageTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterTableInfoRequestDto;
import com.jodongari.handy.protocol.dto.response.GetTableInfoResponseDto;
import com.jodongari.handy.protocol.url.TableInfoApiUrl;
import com.jodongari.handy.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TableInfoController {

    private final TableInfoService tableInfoService;

    @GetMapping(TableInfoApiUrl.TABLE_INFO_GET)
    public List<GetTableInfoResponseDto> getTable(@RequestBody GetTableInfoRequestDto request) {
        return tableInfoService.getTableInfos(request);
    }

    @PostMapping(TableInfoApiUrl.TABLE_INFO_REGISTER)
    public void registerTable(@RequestBody List<RegisterTableInfoRequestDto> request) {
        tableInfoService.registerTableInfo(request);
    }

    @PostMapping(TableInfoApiUrl.TABLE_INFO_MANAGE)
    public void manageTableInfo(@RequestBody ManageTableInfoRequestDto request) {
        tableInfoService.manageTableInfo(request);
    }
}
