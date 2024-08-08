package com.example.est_spring.weekly_4_quiz.controller;

import com.example.est_spring.weekly_4_quiz.dto.MenuDto;
import com.example.est_spring.weekly_4_quiz.dto.StoreDto;
import com.example.est_spring.weekly_4_quiz.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> store = storeService.getAllStores();
        return ResponseEntity.ok(store);
    }

    // 여기서 Optional 사용해도 문제 없나? 근데 Optional이 뭐임.
    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto) {
        StoreDto createStore = storeService.createStore(storeDto);
        return new ResponseEntity<>(createStore, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto) {
        // Optional로 래핑되어야 ResponseEntity::ok 매핑이 된다. 이유는?
        return storeService.updateStore(id, storeDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        boolean deleteStore = storeService.deleteStore(id);
        return deleteStore ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }



}
