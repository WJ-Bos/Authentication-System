package com.wjbos.userregistermail.exeptions;

public class NotValidEmailException extends RuntimeException {
    public NotValidEmailException(String message) {
        super(message);
    }
}
