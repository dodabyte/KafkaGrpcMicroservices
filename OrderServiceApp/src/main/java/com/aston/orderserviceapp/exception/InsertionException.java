package com.aston.orderserviceapp.exception;

public class InsertionException extends Exception {
    public InsertionException() {
        this("");
    }

    public InsertionException(Exception exception) {
        super(exception);
    }

    public InsertionException(String message) {
        super(message);
    }
}
