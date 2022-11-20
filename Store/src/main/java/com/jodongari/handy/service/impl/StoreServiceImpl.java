package com.jodongari.handy.service.impl;

import com.jodongari.handy.domain.store.Store;
import com.jodongari.handy.infrastructure.repository.StoreRepository;
import com.jodongari.handy.protocol.dto.model.StoreModel;
import com.jodongari.handy.protocol.dto.request.GetStoreInfosRequestDto;
import com.jodongari.handy.protocol.dto.request.UpdateStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreInfoResponseDto;
import com.jodongari.handy.protocol.dto.request.DeleteStoreRequestDto;
import com.jodongari.handy.protocol.dto.request.GetStoreRequestDto;
import com.jodongari.handy.protocol.dto.request.GetStoresRequestDto;
import com.jodongari.handy.protocol.dto.request.RegisterStoreRequestDto;
import com.jodongari.handy.protocol.dto.response.GetStoreResponseDto;
import com.jodongari.handy.protocol.dto.response.RegisterStoreResponseDto;
import com.jodongari.handy.service.StoreService;
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
public class StoreServiceImpl implements StoreService {

    private final ModelMapper modelMapper;
    private final StoreRepository storeRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterStoreResponseDto registerStore(RegisterStoreRequestDto request) {
        final StoreModel storeModel = modelMapper.map(request, StoreModel.class);

        final Store store = Store.create(storeModel);
        final Store result = storeRepository.save(store);

        return modelMapper.map(result, RegisterStoreResponseDto.class);
    }

    @Override
    public GetStoreResponseDto getStore(GetStoreRequestDto request) {
        final Store result = storeRepository.findById(request.getStoreSeq()).orElseThrow();

        final StoreModel storeModel = modelMapper.map(result, StoreModel.class);

        return modelMapper.map(storeModel, GetStoreResponseDto.class);
    }

    @Override
    public List<GetStoreResponseDto> getStores(GetStoresRequestDto request) {
        final List<Store> result = storeRepository.findAllByOwnerSeq(request.getOwnerSeq());

        final List<StoreModel> storeModels = result.stream()
                .map(store -> modelMapper.map(store, StoreModel.class))
                .collect(Collectors.toList());

        final List<GetStoreResponseDto> response = storeModels.stream()
                .map(storeModel -> modelMapper.map(storeModel, GetStoreResponseDto.class))
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public List<GetStoreInfoResponseDto> getStoreInfos(GetStoreInfosRequestDto request) {
        final List<Store> stores = storeRepository.findAllByOwnerSeq(request.getOwnerSeq());

        return stores.stream()
                .map(store -> modelMapper.map(store, GetStoreInfoResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStore(UpdateStoreRequestDto request) {
        final Store store = storeRepository.findById(request.getStoreSeq()).orElseThrow();

        store.updateStore(
                request.getName(),
                request.getAddress(),
                request.getTelNumber(),
                request.getIntroduction(),
                request.getOpenTime(),
                request.getDayOff(),
                request.getOriginCountry(),
                request.getCategory());
    }

    @Override
    public void deleteStore(DeleteStoreRequestDto request) {
        storeRepository.deleteById(request.getStoreSeq());
    }
}
