package com.example.est_spring.day_2;

public class EmailService {

    // final 은 한번 초기화 되면 변경 불가. 안전성을 보장한다. 어지간하면 붙여주는게 좋다.
    private final EmailSender emailSender;

    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        emailSender.sendEmail(to, subject, body);
    }

}
