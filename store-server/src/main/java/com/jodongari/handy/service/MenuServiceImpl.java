package com.jodongari.handy.service;

import com.jodongari.handy.api.protocol.dto.exception.MenuNotFoundException;
import com.jodongari.handy.api.protocol.dto.model.ExtraOptionGroupModel;
import com.jodongari.handy.api.protocol.dto.model.ExtraOptionModel;
import com.jodongari.handy.api.protocol.dto.model.MenuModel;
import com.jodongari.handy.api.protocol.dto.model.MenuOptionModel;
import com.jodongari.handy.api.protocol.dto.request.GetMenusByMenuSeqRequestDto;
import com.jodongari.handy.api.protocol.dto.request.ManageMenuRequestDto;
import com.jodongari.handy.api.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.domain.menu.ExtraOption;
import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.Menu;
import com.jodongari.handy.domain.menu.MenuOption;
import com.jodongari.handy.infrastructure.repository.ExtraOptionGroupRepository;
import com.jodongari.handy.infrastructure.repository.ExtraOptionRepository;
import com.jodongari.handy.infrastructure.repository.MenuOptionRepository;
import com.jodongari.handy.infrastructure.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ExtraOptionGroupRepository extraOptionGroupRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final ExtraOptionRepository extraOptionRepository;
    private final MenuDomainService menuDomainService;
    private final ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    // TODO - 22.10.09, Response의 큰 차이로 인해 Image 업로드는 비동기를 이용하는 게 맞다.
    public void manageMenu(List<ManageMenuRequestDto> requests) {
        requests.forEach(request -> {
            final MenuModel menuModel = modelMapper.map(request, MenuModel.class);
            Menu menu;
            if (menuDomainService.isNew(menuModel.getSeq())) {
                menu = Menu.create(menuModel);
            } else {
                menu = Menu.merge(menuModel);
            }
            final Menu menuResult = menuRepository.save(menu);

            final Long menuSeq = menuResult.getSeq();

            final List<MenuOptionModel> menuOptionModels = menuModel.getMenuOptionModels();
            menuOptionModels.forEach(menuOptionModel -> {
                MenuOption menuOption;
                if (menuDomainService.isNew(menuOptionModel.getSeq())) {
                    menuOption = MenuOption.create(menuResult.getSeq(), menuOptionModel);
                } else {
                    menuOption = MenuOption.merge(menuOptionModel);
                }
                menuOptionRepository.save(menuOption);
            });

            final List<ExtraOptionGroupModel> extraOptionGroupModels = menuModel.getExtraOptionGroupModels();
            extraOptionGroupModels.forEach(extraOptionGroupModel -> {
                ExtraOptionGroup extraOptionGroup;
                if (menuDomainService.isNew(extraOptionGroupModel.getSeq())) {
                    extraOptionGroup = ExtraOptionGroup.create(menuSeq, extraOptionGroupModel);
                } else {
                    extraOptionGroup = ExtraOptionGroup.merge(extraOptionGroupModel);
                }
                final ExtraOptionGroup result = extraOptionGroupRepository.save(extraOptionGroup);

                final Long extraOptionGroupSeq = result.getSeq();

                final List<ExtraOptionModel> extraOptionModels = extraOptionGroupModel.getExtraOptionModels();
                extraOptionModels.forEach(extraOptionModel -> {
                    ExtraOption extraOption;
                    if (menuDomainService.isNew(extraOptionModel.getSeq())) {
                        extraOption = ExtraOption.create(extraOptionGroupSeq, extraOptionModel);
                    } else {
                        extraOption = ExtraOption.merge(extraOptionModel);
                    }
                    extraOptionRepository.save(extraOption);
                });
            });
        });
    }

    public List<GetMenuResponseDto> getMenusByStore(Long storeSeq) {
        final List<Menu> menus = menuRepository.findAllByStoreSeq(storeSeq);

        return menus.stream().map(menu -> {
            final GetMenuResponseDto response = modelMapper.map(menu, GetMenuResponseDto.class);
            response.setMenuOptionModels(getMenuOptionModels(menu.getSeq()));
            response.setExtraOptionGroupModels(getExtraOptionGroupModels(menu.getSeq()));
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public List<GetMenuResponseDto> getMenusByMenuSeq(GetMenusByMenuSeqRequestDto request) {
        final List<Long> menuSeqList = request.getMenuSeq();

        return menuSeqList.stream().map(menuSeq -> {
            final Menu menu = menuRepository.findById(menuSeq).orElse(null);

            if (menu == null) {
                log.warn("menu is not exist. menuSeq = {}", menuSeq);
                throw new MenuNotFoundException("Menu is not exist");
            }
            final GetMenuResponseDto response = modelMapper.map(menu, GetMenuResponseDto.class);
            response.setMenuOptionModels(getMenuOptionModels(menu.getSeq()));
            response.setExtraOptionGroupModels(getExtraOptionGroupModels(menu.getSeq()));
            return response;
        }).collect(Collectors.toList());
    }

    private List<MenuOptionModel> getMenuOptionModels(Long menuSeq) {
        final List<MenuOption> menuOptions = menuOptionRepository.findAllByMenuSeq(menuSeq);
        return menuOptions.stream()
                .map(menuOption -> modelMapper.map(menuOption, MenuOptionModel.class))
                .collect(Collectors.toList());
    }

    private List<ExtraOptionGroupModel> getExtraOptionGroupModels(Long menuSeq) {
        final List<ExtraOptionGroup> extraOptionGroups = extraOptionGroupRepository.findAllByMenuSeq(menuSeq);
        return extraOptionGroups.stream().map(extraOptionGroup -> {
            ExtraOptionGroupModel extraOptionGroupModel = modelMapper.map(extraOptionGroup, ExtraOptionGroupModel.class);

            final List<ExtraOption> extraOptions = extraOptionRepository.findAllByExtraOptionGroupSeq(extraOptionGroup.getSeq());
            final List<ExtraOptionModel> extraOptionModels = extraOptions.stream()
                    .map(extraOption -> modelMapper.map(extraOption, ExtraOptionModel.class))
                    .collect(Collectors.toList());
            extraOptionGroupModel.setExtraOptionModels(extraOptionModels);
            return extraOptionGroupModel;
        }).collect(Collectors.toList());
    }
}
