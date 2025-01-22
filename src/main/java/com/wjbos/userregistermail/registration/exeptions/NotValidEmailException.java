package com.wjbos.userregistermail.registration.exeptions;

public class NotValidEmailException extends RuntimeException {
    public NotValidEmailException(String message) {
        super(message);
    }
}
