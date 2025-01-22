package com.wjbos.userregistermail.registration.response;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class InvalidEmailResponse {
    private HttpStatus errorCode;
    private String errorMessage;
}
