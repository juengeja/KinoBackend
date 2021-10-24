package com.example.kinoticketreservierungssystem.controller;

import com.example.kinoticketreservierungssystem.blSupport.Mail;
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
        sendmail.sendMailWithAttachments(new Mail("binhdich@web.de","testtitle","testmsg"));
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}
