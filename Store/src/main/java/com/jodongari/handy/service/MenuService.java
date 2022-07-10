package com.jodongari.handy.service;

import com.jodongari.handy.domain.entity.ExtraOptionEntity;
import com.jodongari.handy.domain.entity.ExtraOptionGroupEntity;
import com.jodongari.handy.domain.entity.MenuEntity;
import com.jodongari.handy.domain.entity.MenuOptionEntity;
import com.jodongari.handy.protocol.model.menu.ExtraOption;
import com.jodongari.handy.protocol.model.menu.Menu;
import com.jodongari.handy.protocol.model.menu.MenuOption;
import com.jodongari.handy.protocol.requestDto.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.responseDto.RegisterMenuResponseDto;
import com.jodongari.handy.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(rollbackFor = Exception.class)
    public RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request, MultipartFile imageFile) {

        Long storeSeq = request.getStoreSeq();
        Menu menu = request.getMenu();

        // 중간에 image module을 통한 url 넣어주는 과정 필요
        String imageURL = null;

        MenuEntity menuEntity = menu.dtoToMenuEntity(storeSeq, imageURL);
        List<MenuOptionEntity> menuOptionEntities = menu.getMenuOptions().stream()
                                                        .map(MenuOption::dtoToMenuOptionEntity)
                                                        .collect(Collectors.toList());

        List<ExtraOptionGroupEntity> extraOptionGroupEntities = menu.getExtraOptionGroups().stream()
                .map(x -> {
                    ExtraOptionGroupEntity extraOptionGroup = x.dtoToExtraOptionGroupEntity();
                    List<ExtraOptionEntity> extraOptionEntities = x.getExtraOptions().stream()
                            .map(ExtraOption::dtoToExtraOptionEntity)
                            .collect(Collectors.toList());
                    extraOptionGroup.addAllExtraOption(extraOptionEntities);
                    return extraOptionGroup;
                }).collect(Collectors.toList());

        menuEntity.addAllMenuOption(menuOptionEntities);
        menuEntity.addAllExtraOptionGroup(extraOptionGroupEntities);
        MenuEntity resultEntity = menuRepository.save(menuEntity);

        return new RegisterMenuResponseDto(null);
    }
}
