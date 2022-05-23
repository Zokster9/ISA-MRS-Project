package com.example.projectmrsisa.service;

import com.example.projectmrsisa.dto.ReportDTO;
import com.example.projectmrsisa.dto.ReservationDTO;
import com.example.projectmrsisa.dto.RetreatDTO;
import com.example.projectmrsisa.dto.UserDTO;
import com.example.projectmrsisa.model.Client;
import com.example.projectmrsisa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    //private Queue<SimpleMailMessage> mails = new ArrayDeque<SimpleMailMessage>();

    @Async
    public void sendApprovedRevisionEmail(String revision, double serviceRating, double ownerRating, User privilegedUser, Client client){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Approved revision confirmation");
        mail.setText("Dear " + privilegedUser.getName() + " " + privilegedUser.getSurname() +
                ", revision from client " + client.getName() + " " + client.getSurname() + " has been accepted. In the revision, client gave you a rating of "
                + Double.toString(serviceRating) + ", your service received a rating of " + Double.toString(ownerRating) + ", while revision text is: " + revision);
        javaMailSender.send(mail);
    }

    @Async
    public void sendReservationConfirmation(ReservationDTO reservationDTO) throws MessagingException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Reservation approved");
        mail.setText("Dear " + reservationDTO.getClientName() + " " + reservationDTO.getClientSurname() +
                " your reservation for " + reservationDTO.getServiceName() + ", from " + reservationDTO.getFromDate() +
                " to " + reservationDTO.getToDate() + ", has been approved.");
        //mails.add(mail);
        javaMailSender.send(mail);
    }

    @Async
    public void sendActivationEmail(UserDTO user) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(env.getProperty("spring.mail.username"));
        helper.setFrom("isamrsprojekat@gmail.com");
        helper.setSubject("Account activation");
        helper.setText(buildEmail(user.getName() + " " + user.getSurname(), user.getId()), true);
        javaMailSender.send(mimeMessage);
    }

    private String buildEmail(String name, int id) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hello " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + "http://localhost:8081/#/activate-account/" + id + "\">Activate Now</a> </p></blockquote>\n <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

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

    @Async
    public void sendSubscriptionEmails(List<String> emails) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Fast reservation for your subscription");
        mail.setText("Fast reservation has been added for service that you have been subscribed. Go check it out.");
        for (String email: emails) {
            javaMailSender.send(mail);
        }
    }

    @Async
    public void confirmReport(ReportDTO report){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Report status update- Accepted");
        mail.setText("Report from " + report.getOwnerName() + " " + report.getOwnerSurname() + " about client " + report.getClientName() + " " + report.getClientSurname()
        + " has been accepted. Text of report: " + report.getReport());
        javaMailSender.send(mail); //privilegovanom korisniku
        javaMailSender.send(mail); //klijentu
    }

    @Async
    public void declineReport(ReportDTO report){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(env.getProperty("spring.mail.username"));
        mail.setFrom("isamrsprojekat@gmail.com");
        mail.setSubject("Report status update- Declined");
        mail.setText("Report from " + report.getOwnerName() + " " + report.getOwnerSurname() + " about client " + report.getClientName() + " " + report.getClientSurname()
                + " has been declined. Text of report: " + report.getReport());
        javaMailSender.send(mail); //privilegovanom korisniku
        javaMailSender.send(mail); //klijentu
    }

    @Async
    public void sendComplaintEmailClient(String complaint, Client client){
        SimpleMailMessage mailClient = new SimpleMailMessage();
        mailClient.setTo(env.getProperty("spring.mail.username"));
        mailClient.setFrom("isamrsprojekat@gmail.com");
        mailClient.setSubject("Complaint answer");
        mailClient.setText("Dear " + client.getName() + " " + client.getSurname() + ", this is admin's response to your complaint: " + complaint);
        javaMailSender.send(mailClient);
    }

    @Async
    public void sendComplaintEmailPrivilegedUser(String complaint, User privilegedUser){
        SimpleMailMessage mailPrivilegedUser = new SimpleMailMessage();
        mailPrivilegedUser.setTo(env.getProperty("spring.mail.username"));
        mailPrivilegedUser.setFrom("isamrsprojekat@gmail.com");
        mailPrivilegedUser.setSubject("Complaint answer");
        mailPrivilegedUser.setText("Dear " + privilegedUser.getName() + " " + privilegedUser.getSurname() +
                ", this is admin's response to a complaint that was written about your service: " + complaint);
        javaMailSender.send(mailPrivilegedUser);
    }
}
