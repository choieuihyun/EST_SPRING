package com.example.est_spring.weekly_4_quiz.dto;

import com.example.est_spring.weekly_4_quiz.entity.Store;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {

    Long id;
    String name;
    String address;
    String phoneNumber;

    public static StoreDto fromEntity(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }

    public Store toEntity() {
        return Store.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .build();
    }



}