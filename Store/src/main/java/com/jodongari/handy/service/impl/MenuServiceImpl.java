package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.menu.ExtraOption;
import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.Menu;
import com.jodongari.handy.domain.menu.MenuOption;
import com.jodongari.handy.protocol.MenuDTO;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import com.jodongari.handy.infrastructure.repository.MenuRepository;
import com.jodongari.handy.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Transactional(rollbackFor = Exception.class)
    public RegisterMenuResponseDto registerMenu(MenuDTO request, MultipartFile imageFile) {

//        Long storeSeq = request.getStoreSeq();

        // 중간에 image module을 통한 url 넣어주는 과정 필요
        String imageURL = null;

//        Menu menu = Menu.builder()
//                .storeSeq(storeSeq)
//                .image(imageUrl)
//                .build();
//
//
//        List<MenuOption> menuOptions = menu.getMenuOptions().stream()
//                                                        .map(MenuOption::dtoToMenuOption)
//                                                        .collect(Collectors.toList());
//
//        List<ExtraOptionGroup> extraOptionGroupEntities = menu.getExtraOptionGroups().stream()
//                .map(x -> {
//                    ExtraOptionGroup extraOptionGroup = x.dtoToExtraOptionGroup();
//                    List<ExtraOption> extraOptionEntities = x.getExtraOption().stream()
//                            .map(ExtraOption::dtoToExtraOption)
//                            .collect(Collectors.toList());
//                    extraOptionGroup.addAllExtraOption(extraOptionEntities);
//                    return extraOptionGroup;
//                }).collect(Collectors.toList());
//
//        Menu.addAllMenuOption(menuOptions);
//        Menu.addAllExtraOptionGroup(extraOptionGroupEntities);
//        Menu result = menuRepository.save(Menu);

        return new RegisterMenuResponseDto(null);
    }

    public GetMenuResponseDto getMenu(Long storeSeq) throws Exception {
//        Menu result = menuRepository.findBySeq(storeSeq).orElseThrow(Exception::new);
//        List<MenuOption> menuOptions = result.getMenuOptions();
//        List<ExtraOptionGroup> extraOptionGroups = result.getExtraOptionGroups();
//        List<ExtraOption> extraOptions = extraOptionGroups.get(0).getExtraOption();
        return null;
    }
}
