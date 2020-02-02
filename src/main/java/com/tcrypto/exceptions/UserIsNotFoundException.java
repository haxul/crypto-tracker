package com.tcrypto.exceptions;

public class UserIsNotFoundException extends RuntimeException {
    public UserIsNotFoundException(String message) {
        super(message);
    }

    public UserIsNotFoundException() {}
}
