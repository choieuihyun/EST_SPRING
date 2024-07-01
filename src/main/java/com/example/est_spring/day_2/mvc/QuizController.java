package com.example.est_spring.day_2.mvc;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuizController {

    private Quiz quiz;
    private QuizView quizView;

    public void startQuiz(){
        String question = quiz.getQuestion();
        String answer = quiz.getAnswer();

        if(question.equals(answer)) {
            quizView.displayResult(true);
        } else
            quizView.displayResult(false);

    }

}
