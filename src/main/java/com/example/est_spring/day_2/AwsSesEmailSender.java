package com.example.est_spring.day_2;

public class AwsSesEmailSender implements EmailSender {


    @Override
    public void sendEmail(String to, String subject, String body) {
        // AwsSes Logic
        System.out.println("Sending AwsSes email to " + to);
    }
}
