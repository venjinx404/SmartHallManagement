package com.smarthall.exception;

public class StudentNotRegisteredException extends RuntimeException {

    public StudentNotRegisteredException(String message) {
        super(message);
    }
}