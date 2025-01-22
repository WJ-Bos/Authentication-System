package com.wjbos.userregistermail.registration.response;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
public class EmailInUseResponse {
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;
}
