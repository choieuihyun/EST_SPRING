package com.example.est_spring.weekly_4_quiz.dto;

import com.example.est_spring.weekly_4_quiz.entity.Customer;
import com.example.est_spring.weekly_4_quiz.entity.Order;
import com.example.est_spring.weekly_4_quiz.entity.Store;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private Long totalPrice;
    private int orderStatus;
    private Long customerId;
    private Long storeId;
    private List<OrderItemDto> orderItems;

    public static OrderDto fromEntity(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
                .storeId(order.getStore() != null ? order.getStore().getId() : null)
                .build();
    }

    public Order toEntity(Customer customer, Store store) {
        return Order.builder()
                .id(this.id)
                .totalPrice(this.totalPrice)
                .orderStatus(this.orderStatus)
                .customer(customer)
                .store(store)
                .build();
    }

}