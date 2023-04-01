package com.example.projectstore.api.exceptions;

public class NotAuthorizedException extends RuntimeException {
        public NotAuthorizedException(String message) {
            super(message);
        }
    }

