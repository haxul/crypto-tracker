package com.tcrypto.exceptions;

public class AccessTokenIsNotFoundException extends RuntimeException {
    public AccessTokenIsNotFoundException(String message) {
        super(message);
    }

    public AccessTokenIsNotFoundException(){}
}
