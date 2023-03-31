package com.example.projectstore.api.exceptions;

public class UserNotMatchOrderException extends RuntimeException {
    public UserNotMatchOrderException(String message) {
        super(message);
    }
}
