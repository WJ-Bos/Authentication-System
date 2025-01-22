package com.wjbos.userregistermail.exeptions;

import com.wjbos.userregistermail.registration.response.InvalidEmailResponse;
import com.wjbos.userregistermail.shared.response.GlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponse> handleGlobalException(final Exception ex, final WebRequest request) {
        GlobalErrorResponse globalErrorResponse = new GlobalErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(globalErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotValidEmailException.class)
    public ResponseEntity<InvalidEmailResponse> notValidEmailException(NotValidEmailException ex , WebRequest request) {
        InvalidEmailResponse invalidEmailResponse = new InvalidEmailResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity<>(invalidEmailResponse, HttpStatus.BAD_REQUEST);
    }

}
