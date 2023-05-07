package com.jodongari.handy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableInfoDomainServiceImpl implements TableInfoDomainService {

    @Override
    public boolean isNew(Long seq) {
        return seq == null;
    }
}
