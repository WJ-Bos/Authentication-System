package com.wjbos.userregistermail.registration.service;

import com.wjbos.userregistermail.registration.dto.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {

    public String register(RegistrationRequest request) {
        return "this Works...Kinda";
    }
}
