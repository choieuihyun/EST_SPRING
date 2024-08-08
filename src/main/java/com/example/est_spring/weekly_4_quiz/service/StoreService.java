package com.example.est_spring.weekly_4_quiz.service;

import com.example.est_spring.weekly_4_quiz.dto.MenuDto;
import com.example.est_spring.weekly_4_quiz.dto.StoreDto;
import com.example.est_spring.weekly_4_quiz.entity.Menu;
import com.example.est_spring.weekly_4_quiz.entity.Store;
import com.example.est_spring.weekly_4_quiz.repository.MenuRepository;
import com.example.est_spring.weekly_4_quiz.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, MenuRepository menuRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public StoreDto createStore(StoreDto storeDto) {
        Store store = storeDto.toEntity();
        Store createdStore = storeRepository.save(store);
        return StoreDto.fromEntity(createdStore);
    }

    @Transactional(readOnly = true)
    public List<StoreDto> getAllStores() {
        // stream은 List일때만 사용가능? 왜 getStoreById는 Optional 사용함?
        return storeRepository.findAll().stream()
                .map(StoreDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<StoreDto> getStoreById(Long storeId) {
        return storeRepository.findById(storeId).map(StoreDto::fromEntity);
    }

    // 이거 코드 해석이 좀 궁금하네.
    @Transactional
    public Optional<StoreDto> updateStore(Long storeId, StoreDto storeDto) {
        return storeRepository.findById(storeId)
                .map(existingStore -> {
                    existingStore.setName(storeDto.getName());
                    existingStore.setAddress(storeDto.getAddress());
                    existingStore.setPhoneNumber(storeDto.getPhoneNumber()
                    );
                    return StoreDto.fromEntity(existingStore);
                });
    }

    @Transactional
    public boolean deleteStore(Long storeId) {
        return storeRepository.findById(storeId)
                .map(store -> {
                    storeRepository.delete(store);
                    return true;
                })
                .orElse(false);
    }

//    위에꺼랑 차이가 뭘까?
//    public StoreDto getStoreById(Long id) {
//        Store store = storeRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Store not found"));
//        return StoreDto.fromEntity(store);
//    }

}
