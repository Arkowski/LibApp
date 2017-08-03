package com.ak.service;

/**
 * Created by Arkowski on 2017-07-08.
 */

public interface EmailService {

    void sendEmail(String fromAddress, String toAddress, String subject, String body);
}

