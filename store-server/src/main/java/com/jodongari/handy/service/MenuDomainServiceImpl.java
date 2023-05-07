package com.jodongari.handy.service;

import com.jodongari.handy.service.MenuDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuDomainServiceImpl implements MenuDomainService {

    @Override
    public boolean isNew(Long seq) {
        return seq == null;
    }
}
