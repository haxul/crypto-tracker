package com.tcrypto.dto.response;

import java.util.Date;

public class ErrorMessage {
    private Date date;
    private String message;

    public ErrorMessage() {}
    public ErrorMessage(String message, Date date) {
        this.message = message;
        this.date = date;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
