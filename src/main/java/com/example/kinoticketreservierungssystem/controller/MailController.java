package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.blSupport.Mail;
import com.example.kinoticketreservierungssystem.entity.Booking;
import com.example.kinoticketreservierungssystem.entity.Customer;
import com.example.kinoticketreservierungssystem.service.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/mail/")
public class MailController {

    @Autowired
    SendMail sendmail;

    public MailController(SendMail service) {
        this.sendmail = service;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMail() {
        sendmail.sendMail(new Mail("binhdich@web.de","testtitle","testmsg"));
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail() throws MessagingException {
        Booking booking = new Booking();
        Customer customer = new Customer();
        customer.setEmail("binhdich@web.de");
        customer.setFirstName("binh");
        booking.setCustomerInfo(customer);
        sendmail.ticketEmail(booking);
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}
