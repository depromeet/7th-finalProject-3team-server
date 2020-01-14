package com.depromeet.watni.exception;

public class NotFoundException extends RuntimeException {
    private NotFoundException() { }

    public NotFoundException(String message) {
        super(message);
    }
}
