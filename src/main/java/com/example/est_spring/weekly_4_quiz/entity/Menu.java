package com.example.est_spring.weekly_4_quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Menu")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    // 새로운 Menu 객체를 생성하는 메서드
    public Menu withStore(Store store) {
        return Menu.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .description(this.description)
                .category(this.category)
                .store(store)
                .build();
    }

    // 아 원래는 더티체킹 어노테이션 사용해서 변하는것만 update 쿼리 날려서 변경시켜줄 수 있는데
    // 이거는 수동 더티체킹이구나. 그래서 이렇게 한거구나.
    public void update(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

}
