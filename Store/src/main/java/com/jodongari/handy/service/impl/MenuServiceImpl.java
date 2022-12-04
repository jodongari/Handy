package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.menu.Menu;
import com.jodongari.handy.infrastructure.repository.MenuRepository;
import com.jodongari.handy.protocol.dto.model.MenuModel;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import com.jodongari.handy.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    // TODO - 22.10.09, Response의 큰 차이로 인해 Image 업로드는 비동기를 이용하는 게 맞다.
    public RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request) {

        final MenuModel menuModel = modelMapper.map(request, MenuModel.class);

        final Menu menu = Menu.create(menuModel);
        final Menu result = menuRepository.save(menu);

        return modelMapper.map(result, RegisterMenuResponseDto.class);
    }

    public GetMenuResponseDto getMenu(Long storeSeq) throws Exception {
        final Menu result = menuRepository.findBySeq(storeSeq).orElseThrow(Exception::new);

        return modelMapper.map(result, GetMenuResponseDto.class);
    }
}
