package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.menu.ExtraOptionGroup;
import com.jodongari.handy.domain.menu.Menu;
import com.jodongari.handy.domain.menu.MenuOption;
import com.jodongari.handy.file.FileObjectStorageService;
import com.jodongari.handy.infrastructure.repository.MenuRepository;
import com.jodongari.handy.protocol.dto.model.MenuModel;
import com.jodongari.handy.protocol.dto.request.RegisterMenuRequestDto;
import com.jodongari.handy.protocol.dto.response.GetMenuResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterMenuResponseDto;
import com.jodongari.handy.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class MenuServiceImpl implements MenuService {

    private final FileObjectStorageService fileObjectStorageService;
    private final MenuRepository menuRepository;

    @Transactional(rollbackFor = Exception.class)
    public RegisterMenuResponseDto registerMenu(RegisterMenuRequestDto request, MultipartFile imageFile) throws Exception {
        // TODO - 22.10.09, Response의 큰 차이로 인해 Image 업로드는 비동기를 이용하는 게 맞다.
        final String imageUrl = Optional.of(fileObjectStorageService.uploadObjectToS3(imageFile.getResource().getFile()))
                 .orElseThrow(() -> new Exception("Image upload failed"));

        final MenuModel menuModel = request.toModel();
        menuModel.setImageUrl(imageUrl);

        final Menu menu = Menu.create(menuModel);
        final Menu result = menuRepository.save(menu);

        return new RegisterMenuResponseDto(result);
    }

    public GetMenuResponseDto getMenu(Long storeSeq) throws Exception {
        final Menu result = menuRepository.findBySeq(storeSeq).orElseThrow(Exception::new);

        return GetMenuResponseDto.builder()
                .seq(result.getSeq())
                .storeSeq(result.getStoreSeq())
                .name(result.getName().getValue())
                .description(result.getDescription().getValue())
                .imageUrl(result.getImage().getValue())
                .status(result.getStatus())
                .menuOptionModels(result.getMenuOptions().stream().map(MenuOption::toModel).collect(Collectors.toList()))
                .extraOptionGroupModels(result.getExtraOptionGroups().stream().map(ExtraOptionGroup::toModel).collect(Collectors.toList()))
                .build();
    }
}
