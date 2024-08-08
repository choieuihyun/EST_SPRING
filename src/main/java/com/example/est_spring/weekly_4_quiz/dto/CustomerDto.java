package com.example.est_spring.weekly_4_quiz.dto;

import com.example.est_spring.weekly_4_quiz.entity.Customer;
import com.example.est_spring.weekly_4_quiz.entity.Order;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;

    public static CustomerDto fromEntity(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .build();

    }

    public Customer toEntity() {
        return Customer.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .build();

    }
}