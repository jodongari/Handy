package com.jodongari.handy.protocol.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ManageMenuRequestDto {
    List<RegisterMenuRequestDto> registerMenuRequestDtos;


}
