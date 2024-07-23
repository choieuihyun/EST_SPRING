package com.example.est_spring.weekly_4_quiz.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {

    Long id;
    String name;
    String address;
    String phoneNumber;
    List<OrderDto> orderList;

}