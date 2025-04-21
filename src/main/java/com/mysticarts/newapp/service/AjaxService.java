package com.mysticarts.newapp.service;

public interface AjaxService {

    String checkEmail(String email);

    String checkPhone(Long phone);

    String sendOtp(String email);
}
