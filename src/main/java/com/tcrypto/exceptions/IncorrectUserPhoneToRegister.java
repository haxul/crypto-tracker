package com.tcrypto.exceptions;

public class IncorrectUserPhoneToRegister extends RuntimeException {

    public IncorrectUserPhoneToRegister(String message){
        super(message);
    }
}
