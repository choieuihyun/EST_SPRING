package com.example.est_spring.day_2.mvc;

public class QuizView {

    public void displayQuestion(String question) {
        System.out.println(question);
    }

    public void getUserAnswer(String answer) {
        System.out.println(answer);
    }

    public void displayResult(boolean isCorrect) {
        if (isCorrect) {
            System.out.println("정답입니다.");
        } else
            System.out.println("오답입니다.");
    }

}
