package com.jodongari.handy.protocol.dto.request;

import lombok.Value;

import java.util.List;

@Value
public class DeleteQRCodeRequestDto {
    List<String> hashes;
}
