package com.mintdigital.cardscheme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CardVerificationException extends RuntimeException {

    public CardVerificationException(String message){
        super(message);
    }
}


