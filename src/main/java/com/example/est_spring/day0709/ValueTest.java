package com.example.est_spring.day0709;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueTest {

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    public void print() {
        System.out.println("serverPort: " + serverPort);
        System.out.println("applicationName: " + applicationName);
    }

}