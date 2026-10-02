package com.smarthall.exception;

public class AdminLoginException extends RuntimeException {

    public AdminLoginException(String message) {
        super(message);
    }
}

class WrongAdminEmailException extends AdminLoginException {

    public WrongAdminEmailException(String message) {
        super(message);
    }
}

class WrongAdminPasswordException extends AdminLoginException {

    public WrongAdminPasswordException(String message) {
        super(message);
    }
}