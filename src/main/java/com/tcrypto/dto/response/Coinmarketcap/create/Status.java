package com.tcrypto.dto.response.Coinmarketcap.create;

public class Status {
    private String timestamp;
    private float error_code;
    private String error_message = null;
    private float elapsed;
    private float credit_count;
    private String notice = null;


    // Getter Methods

    public String getTimestamp() {
        return timestamp;
    }

    public float getError_code() {
        return error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public float getElapsed() {
        return elapsed;
    }

    public float getCredit_count() {
        return credit_count;
    }

    public String getNotice() {
        return notice;
    }

    // Setter Methods

    public void setTimestamp( String timestamp ) {
        this.timestamp = timestamp;
    }

    public void setError_code( float error_code ) {
        this.error_code = error_code;
    }

    public void setError_message( String error_message ) {
        this.error_message = error_message;
    }

    public void setElapsed( float elapsed ) {
        this.elapsed = elapsed;
    }

    public void setCredit_count( float credit_count ) {
        this.credit_count = credit_count;
    }

    public void setNotice( String notice ) {
        this.notice = notice;
    }
}