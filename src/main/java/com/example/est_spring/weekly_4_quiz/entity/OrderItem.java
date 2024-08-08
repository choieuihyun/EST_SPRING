package com.example.est_spring.weekly_4_quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Order_Item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
