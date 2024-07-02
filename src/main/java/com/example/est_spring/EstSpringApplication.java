package com.example.est_spring;

import com.example.est_spring.day_2.AwsSesEmailSender;
import com.example.est_spring.day_2.EmailService;
import com.example.est_spring.day_2.SmtpEmailSender;
import com.example.est_spring.day_2.mvc.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EstSpringApplication {

    @Value("${kakao.api.key}")
    private String name;

    public static void main(String[] args) {
        Quiz quiz = new Quiz("ggg2", "ggg");
        QuizView quizView = new QuizView();
        QuizController quizController = new QuizController(quiz, quizView);

        quizController.startQuiz();

        //SpringApplication.run(EstSpringApplication.class, args);
    }

}
