package org.example.exception;

public class NotValiedPasswordException extends RuntimeException{
    public NotValiedPasswordException(String message) {
        super(message);
    }
}
