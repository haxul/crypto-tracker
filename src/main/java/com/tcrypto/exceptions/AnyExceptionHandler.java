package com.tcrypto.exceptions;

import com.tcrypto.dto.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AnyExceptionHandler extends ResponseEntityExceptionHandler {

    // I rest this code only as a reminder for my in my next projects ====================
    //    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
//        String text = ex == null ? ex.toString() : ex.getLocalizedMessage();
//        ErrorMessage errorMessage = new ErrorMessage(text, new Date());
//        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(value = {NullPointerException.class})
//    public ResponseEntity<Object> handleAnyException(NullPointerException ex, WebRequest request) {
//        String text = ex == null ? ex.toString() : ex.getLocalizedMessage();
//        ErrorMessage errorMessage = new ErrorMessage(text, new Date());
//        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
//    }
    // =====================================================================================

    @ExceptionHandler(value = {UserAlreadyExistsException.class, IncorrectUserPhoneToRegister.class })
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        String text = ex == null ? ex.toString() : ex.getLocalizedMessage();
        ErrorMessage errorMessage = new ErrorMessage(text, new Date());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
