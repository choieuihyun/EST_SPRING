package com.example.est_spring.weekly_4_quiz.dto;

import com.example.est_spring.weekly_4_quiz.entity.Menu;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuDto {

    private Long id;
    private String name;
    private String price;
    private String description;
    private Long categoryId;
    private Long storeId;

    public static MenuDto fromEntity(Menu menu) {
        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();
    }

    // 니가 빌더 패턴으로 바꿀 엔티티에 @Builder가 선언되어 있어야함.
    public Menu toEntity() {
        return Menu.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .description(this.description)
                .build();
    }

}
