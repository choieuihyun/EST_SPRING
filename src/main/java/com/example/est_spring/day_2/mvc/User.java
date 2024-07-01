package com.example.est_spring.day_2.mvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // Getter 메서드 생성 어노테이션
@AllArgsConstructor // 생성자 생성 어노테이션
@Setter
public class User {

    private String email;
    private String name;
    private int age;

}
