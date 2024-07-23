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
    private List<Order> orders = new ArrayList<>();

    public static CustomerDto fromEntity(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .phoneNumber(customer.getPhoneNumber())
                .build();

    }

    public Customer toEntity() {
        return new Customer(
                this.id,
                this.name,
                this.address,
                this.phoneNumber,
                this.orders
        );

    }
}