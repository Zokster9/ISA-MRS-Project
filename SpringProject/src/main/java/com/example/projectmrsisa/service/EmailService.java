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
    public void sendTerminationAcceptedEmail(UserDTO user, String acceptedTerminationReason){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Termination accepted");
        mail.setText("Dear " + user.getName() + " " + user.getSurname() + " your account termination has been accepted because of the following reason: " + acceptedTerminationReason);
        //mails.add(mail);
        javaMailSender.send(mail);
    }

    @Async
    public void sendTerminationDeclinedEmail(UserDTO user, String declinedTerminationReason){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Termination declined");
        mail.setText("Dear " + user.getName() + " " + user.getSurname() + " your account termination has been declined because of the following reason: " + declinedTerminationReason);
        //mails.add(mail);
        javaMailSender.send(mail);
    }
}