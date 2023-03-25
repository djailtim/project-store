package com.example.projectstore.api.exceptions;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String message){
        super(message);
    }
}
