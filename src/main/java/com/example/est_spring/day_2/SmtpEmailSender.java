package com.example.est_spring.day_2;

public class SmtpEmailSender implements EmailSender {
    @Override
    public void sendEmail(String to, String subject, String body) {
        // SMTP Logic
        System.out.println("Sending Smtp email to " + to);
    }
}
