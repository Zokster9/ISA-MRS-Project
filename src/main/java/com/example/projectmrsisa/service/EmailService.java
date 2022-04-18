package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Queue;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    //private Queue<SimpleMailMessage> mails = new ArrayDeque<SimpleMailMessage>();

    @Async
    public void sendRegistrationAcceptedEmail(UserDTO user){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Registration accepted");
        mail.setText("Dear " + user.getName() + " " + user.getSurname() + " you have been accepted into our system!");
        //mails.add(mail);
        javaMailSender.send(mail);
    }

    @Async
    public void sendRegistrationDeclinedEmail(UserDTO user, String declinedRegistrationReason){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Registration declined");
        mail.setText("Dear " + user.getName() + " " + user.getSurname() + " your registration reason has been declined because of the following reason: " + declinedRegistrationReason);
        //mails.add(mail);
        javaMailSender.send(mail);
    }

    @Async
    public void sendTerminateRequestEmail(User user) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Termination request notified");
        mail.setText("Dear " + user.getName() + " " + user.getSurname() + " your termination request have been notified. You will receive email when system administrator decides what to do with your account.");
        javaMailSender.send(mail);
    }
}
