package com.example.projectmrsisa.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailThread implements Runnable {

    public void run(SimpleMailMessage mail, JavaMailSender mailSender){
        mailSender.send(mail);
    }

    @Override
    public void run() {

    }
}
