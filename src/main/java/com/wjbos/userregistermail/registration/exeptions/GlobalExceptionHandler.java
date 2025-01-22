package com.wjbos.userregistermail.registration.exeptions;

import com.wjbos.userregistermail.registration.response.InvalidEmailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotValidEmailException.class)
    public ResponseEntity<InvalidEmailResponse> notValidEmailException(NotValidEmailException ex , WebRequest request) {
        InvalidEmailResponse invalidEmailResponse = new InvalidEmailResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity<>(invalidEmailResponse, HttpStatus.BAD_REQUEST);
    }
}
