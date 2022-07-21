package com.jodongari.handy.repository;

import com.jodongari.handy.domain.entity.ExtraOptionEntity;
import com.jodongari.handy.domain.entity.ExtraOptionGroupEntity;
import com.jodongari.handy.domain.entity.MenuEntity;
import com.jodongari.handy.domain.entity.MenuOptionEntity;
import com.jodongari.handy.domain.entity.status.ExtraOptionGroupStatus;
import com.jodongari.handy.domain.entity.status.ExtraOptionStatus;
import com.jodongari.handy.domain.entity.status.MenuOptionStatus;
import com.jodongari.handy.domain.entity.status.MenuStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    static final Long STORE_SEQ = 1L;
    static final String MENU_NAME = "뜩뽀끼";
    static final String MENU_DESCRIPTION = "누구나 먹을 수 있는 뜩뽀끼";
    static final String MENU_IMAGE = "jodongari/testImage";
    static final MenuStatus MENU_STATUS = MenuStatus.OPEN;

    static final String MENU_OPTION_NAME = "소";
    static final Integer MENU_OPTION_PRICE = 100;
    static final MenuOptionStatus MENU_OPTION_STATUS = MenuOptionStatus.OPEN;

    static final String EXTRA_OPTION_GROUP_NAME = "떡";
    static final String EXTRA_OPTION_GROUP_TYPE = "0";
    static final Integer EXTRA_OPTION_GROUP_MIN_SELECT_LIMIT = 1;
    static final Integer EXTRA_OPTION_GROUP_MAX_SELECT_LIMIT = 10;
    static final ExtraOptionGroupStatus EXTRA_OPTION_GROUP_STATUS = ExtraOptionGroupStatus.OPEN;

    static final String EXTRA_OPTION_NAME = "조랭이떡";
    static final Integer EXTRA_OPTION_EXTRA_FEE = 100;
    static final ExtraOptionStatus EXTRA_OPTION_STATUS = ExtraOptionStatus.OPEN;

    @Nested
    @DisplayName("메뉴 test")
    class registerMenuTest {

        private MenuEntity menuEntity;
        private MenuOptionEntity menuOptionEntity;
        private ExtraOptionGroupEntity extraOptionGroupEntity;
        private ExtraOptionEntity extraOptionEntity;

        @Test
        @DisplayName("메뉴가 잘 들어갔는지 확인")
        public void getMenu() {

            //given
            menuEntity = MenuEntity.builder()
                    .storeSeq(STORE_SEQ)
                    .name(MENU_NAME)
                    .description(MENU_DESCRIPTION)
                    .image(MENU_IMAGE)
                    .status(MENU_STATUS)
                    .build();
            menuRepository.save(menuEntity);

            //when
            MenuEntity resultEntity = menuRepository.findAll().get(0);

            //then
            assertEquals(menuEntity, resultEntity);
        }

        @Test
        @DisplayName("메뉴 옵션 잘 들어갔는지 확인")
        public void getMenuOption() {

            //given
            menuEntity = MenuEntity.builder()
                    .storeSeq(STORE_SEQ)
                    .name(MENU_NAME)
                    .description(MENU_DESCRIPTION)
                    .image(MENU_IMAGE)
                    .status(MENU_STATUS)
                    .build();

            menuOptionEntity = MenuOptionEntity.builder()
                    .name(MENU_OPTION_NAME)
                    .price(MENU_OPTION_PRICE)
                    .status(MENU_OPTION_STATUS)
                    .build();
            menuEntity.addMenuOption(menuOptionEntity);
            menuRepository.save(menuEntity);

            //when
            MenuEntity selectedEntity = menuRepository.findAll().get(0);
            MenuOptionEntity selectedMenuOptionEntity = selectedEntity.getMenuOptionEntities().get(0);

            //then
            assertEquals(menuOptionEntity, selectedMenuOptionEntity);

        }

        @Test
        @DisplayName("추가옵션그룹 잘 들어갔는지 확인")
        public void getExtraOptionGroup() {

            //given
            menuEntity = MenuEntity.builder()
                    .storeSeq(STORE_SEQ)
                    .name(MENU_NAME)
                    .description(MENU_DESCRIPTION)
                    .image(MENU_IMAGE)
                    .status(MENU_STATUS)
                    .build();
            extraOptionGroupEntity = ExtraOptionGroupEntity.builder()
                    .name(EXTRA_OPTION_GROUP_NAME)
                    .type(EXTRA_OPTION_GROUP_TYPE)
                    .minSelectLimit(EXTRA_OPTION_GROUP_MIN_SELECT_LIMIT)
                    .maxSelectLimit(EXTRA_OPTION_GROUP_MAX_SELECT_LIMIT)
                    .status(EXTRA_OPTION_GROUP_STATUS)
                    .build();

            menuEntity.addExtraOptionGroup(extraOptionGroupEntity);
            menuRepository.save(menuEntity);

            //when
            MenuEntity selectedEntity = menuRepository.findAll().get(0);
            ExtraOptionGroupEntity selectedExtraOptionGroupEntity = selectedEntity.getExtraOptionGroupEntities().get(0);

            //then
            assertEquals(extraOptionGroupEntity, selectedExtraOptionGroupEntity);

        }

        @Test
        @DisplayName("추가옵션 잘 들어갔는지 확인")
        public void getExtraOption() {

            //given
            menuEntity = MenuEntity.builder()
                    .storeSeq(STORE_SEQ)
                    .name(MENU_NAME)
                    .description(MENU_DESCRIPTION)
                    .image(MENU_IMAGE)
                    .status(MENU_STATUS)
                    .build();

            extraOptionGroupEntity = ExtraOptionGroupEntity.builder()
                    .name(EXTRA_OPTION_GROUP_NAME)
                    .type(EXTRA_OPTION_GROUP_TYPE)
                    .minSelectLimit(EXTRA_OPTION_GROUP_MIN_SELECT_LIMIT)
                    .maxSelectLimit(EXTRA_OPTION_GROUP_MAX_SELECT_LIMIT)
                    .status(EXTRA_OPTION_GROUP_STATUS)
                    .build();

            extraOptionEntity = extraOptionEntity.builder()
                    .name(EXTRA_OPTION_NAME)
                    .extraFee(EXTRA_OPTION_EXTRA_FEE)
                    .status(EXTRA_OPTION_STATUS)
                    .build();

            extraOptionGroupEntity.addExtraOption(extraOptionEntity);
            menuEntity.addExtraOptionGroup(extraOptionGroupEntity);
            menuRepository.save(menuEntity);

            //when
            MenuEntity selectedEntity = menuRepository.findAll().get(0);
            ExtraOptionGroupEntity selectedExtraOptionGroupEntity = selectedEntity.getExtraOptionGroupEntities().get(0);
            ExtraOptionEntity selectedExtraOptionEntity = selectedExtraOptionGroupEntity.getExtraOptionEntities().get(0);

            //then
            assertEquals(extraOptionEntity, selectedExtraOptionEntity);
        }

    }

}