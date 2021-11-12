package com.example.kinoticketreservierungssystem.service;

import com.example.kinoticketreservierungssystem.blSupport.Mail;
import com.example.kinoticketreservierungssystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
class SendMailTest {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketPDF ticketPDF;

    SimpleMailMessage simpleMailMessage;
    Mail mail;
    JavaMailSender testSender;


    @Test
    void sendMail() {

        mail = new Mail("testRecipient", "testSubject", "testMessage");

        assertEquals("testMessage", mail.getMessage());
        assertEquals("testRecipient", mail.getRecipient());
        assertEquals("testSubject", mail.getSubject());
    }

    @Test
    void ticketEmail() {

    }
}