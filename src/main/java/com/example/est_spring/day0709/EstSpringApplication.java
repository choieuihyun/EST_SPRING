package com.example.est_spring.day0709;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.example.est_spring.day0718_daily_quiz")
public class EstSpringApplication {

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct // 이걸 안쓰고 메인에서 그냥 아래의 print를 호출하면 스프링이 다 세팅되기 전에 호출되는거라 int = 0, String = null로 찍힘.
    public void printConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        System.out.println("포트번호: " + port);
        System.out.println("애플리케이션 이름: " + appName);
    }
    public static void main(String[] args) {
        SpringApplication.run(EstSpringApplication.class, args);
    }

}
