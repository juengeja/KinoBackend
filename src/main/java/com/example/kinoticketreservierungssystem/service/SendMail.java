package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.Mail;
import com.example.kinoticketreservierungssystem.entity.Booking;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMail{
    private final JavaMailSender javaMailSender;

    public SendMail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendMail(Mail mail) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getRecipient(), mail.getRecipient());

        msg.setSubject(mail.getSubject());
        msg.setText(mail.getMessage());

        javaMailSender.send(msg);
    }

    public void ticketEmail(Booking booking) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(booking.getCustomerInfo().getEmail());

        helper.setSubject("Indigo BW Movie Tickets");

        helper.setText("Dear " + booking.getCustomerInfo().getFirstName() + ",\n\n"+ "we wish you all the best enjoyment for your upcoming movie. Please find the attached tickets.", true);

        helper.addAttachment("ticket.pdf", new ClassPathResource("ticket.pdf"));

        javaMailSender.send(msg);
    }
}

