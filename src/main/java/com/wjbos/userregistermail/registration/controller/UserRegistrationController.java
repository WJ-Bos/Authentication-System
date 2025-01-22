package com.wjbos.userregistermail.registration.controller;

import com.wjbos.userregistermail.registration.dto.RegistrationRequest;
import com.wjbos.userregistermail.registration.service.RegisterUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {

    RegisterUserService registerUserService;

    public UserRegistrationController() {
        this.registerUserService = new RegisterUserService();
    }

    public String registerUser(@RequestBody RegistrationRequest request){
        return registerUserService.register(request);
    }
}
