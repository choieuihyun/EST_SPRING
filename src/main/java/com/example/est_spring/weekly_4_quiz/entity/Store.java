package com.example.est_spring.weekly_4_quiz.entity;

import com.example.est_spring.weekly_4_quiz.dto.StoreDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Store")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "store")
    private List<Order> orderList;

    @OneToMany(mappedBy = "store")
    private List<Menu> menuList;

//    public Store updateStore(StoreDto storeDto) {
//        return Store.builder().build();
//    }

}
