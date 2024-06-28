package com.example.est_spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstSpringApplication {

    @Value("${kakao.api.key}")
    private String name;

    public static void main(String[] args) {
        UserRepositoryInterface userRepository = new UserRepository();
        SpringApplication.run(EstSpringApplication.class, args);
    }

}
