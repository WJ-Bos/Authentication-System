package com.wjbos.userregistermail.shared.exeptions;

public class NotValidEmailException extends RuntimeException {
    public NotValidEmailException(String message) {
        super(message);
    }
}
