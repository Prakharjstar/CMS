package com.cms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cms.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender eMailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {

         SimpleMailMessage message= new SimpleMailMessage();
         message.setTo(to);
         message.setSubject(subject);
         message.setText(body);
         message.setFrom("scm@demomailtrap.com" 
                          );
         eMailSender.send(message);
      
    }

    @Override
    public void sendEmailWithhtml() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithhtml'");
    }

    @Override
    public void sendEmailWithAttachment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmailWithAttachment'");
    }

}
