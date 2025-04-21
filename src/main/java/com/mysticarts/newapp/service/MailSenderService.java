package com.mysticarts.newapp.service;

import javax.xml.soap.SAAJResult;

public interface MailSenderService {

    boolean sendRegistrationEmail(String subject , String bodyOfMsg, String email);


}
