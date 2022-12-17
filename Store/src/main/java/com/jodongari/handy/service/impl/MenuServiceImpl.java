package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.menu.ExtraOption;
import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.Menu;
import com.jodongari.handy.domain.menu.MenuOption;
import com.jodongari.handy.infrastructure.repository.ExtraOptionGroupRepository;
import com.jodongari.handy.infrastructure.repository.ExtraOptionRepository;
import com.jodongari.handy.infrastructure.repository.MenuOptionRepository;
import com.jodongari.handy.infrastructure.repository.MenuRepository;
import com.jodongari.handy.protocol.dto.model.ExtraOptionGroupModel;
import com.jodongari.handy.protocol.dto.model.ExtraOptionModel;
import com.jodongari.handy.protocol.dto.model.MenuModel;
import com.jodongari.handy.protocol.dto.model.MenuOptionModel;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ExtraOptionGroupRepository extraOptionGroupRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final ExtraOptionRepository extraOptionRepository;

    private final ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    // TODO - 22.10.09, Response의 큰 차이로 인해 Image 업로드는 비동기를 이용하는 게 맞다.
    public void registerMenu(RegisterMenuRequestDto request) {

        final MenuModel menuModel = modelMapper.map(request, MenuModel.class);

        final Menu menu = Menu.create(menuModel);
        final Menu menuResult = menuRepository.save(menu);

        final List<MenuOptionModel> menuOptionModels = menuModel.getMenuOptionModels();
        menuOptionModels.forEach(menuOptionModel -> {
            menuOptionModel.addMenuSeq(menuResult.getSeq());
            MenuOption menuoption = MenuOption.create(menuOptionModel);
            menuOptionRepository.save(menuoption);
        });

        final List<ExtraOptionGroupModel> extraOptionGroupModels = menuModel.getExtraOptionGroupModels();
        extraOptionGroupModels.forEach(extraOptionGroupModel -> {
            extraOptionGroupModel.addMenuSeq(menuResult.getSeq());
            final ExtraOptionGroup extraOptionGroup = ExtraOptionGroup.create(extraOptionGroupModel);
            final ExtraOptionGroup result = extraOptionGroupRepository.save(extraOptionGroup);

            final List<ExtraOptionModel> extraOptionModels = extraOptionGroupModel.getExtraOptionModels();
            extraOptionModels.forEach(extraOptionModel -> {
                extraOptionModel.addExtraOptionGroupSeq(result.getSeq());
                ExtraOption extraOption = ExtraOption.create(extraOptionModel);
                extraOptionRepository.save(extraOption);
            });
        });
    }

    public GetMenuResponseDto getMenu(Long storeSeq) throws Exception {
        final Menu result = menuRepository.findBySeq(storeSeq).orElseThrow(Exception::new);

        return modelMapper.map(result, GetMenuResponseDto.class);
    }
}
