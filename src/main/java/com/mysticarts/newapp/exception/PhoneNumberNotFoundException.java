package com.mysticarts.newapp.exception;

public class PhoneNumberNotFoundException extends RuntimeException {
    public PhoneNumberNotFoundException(String message) {
        super("Phone Number is not found");
    }
}
