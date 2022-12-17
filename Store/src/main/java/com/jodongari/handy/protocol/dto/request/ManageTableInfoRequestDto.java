package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ManageTableInfoRequestDto {
    List<RegisterTableInfoRequestDto> registerTableInfosDto;
    List<UpdateTableInfoRequestDto> updateTableInfosDto;
    List<DeleteTableInfoRequestDto> deleteTableInfosDto;
}
