package com.example.est_spring.weekly_4_quiz.dto;

import com.example.est_spring.weekly_4_quiz.entity.Category;
import lombok.*;

import java.io.Serializable;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    Long id;
    String name;

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category toEntity() {
        return Category.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }

}