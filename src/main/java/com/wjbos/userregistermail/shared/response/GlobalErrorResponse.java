package com.wjbos.userregistermail.shared.response;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
public class GlobalErrorResponse {
    private HttpStatus statusCode;
    private String message;
    private LocalDateTime timestamp;
}
