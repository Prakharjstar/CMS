package com.cms.services;

public interface EmailService {

    void sendEmail(String to, String subject, String body);


    void sendEmailWithhtml();
    void sendEmailWithAttachment();

}
