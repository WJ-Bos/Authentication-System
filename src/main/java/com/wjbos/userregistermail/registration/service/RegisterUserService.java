package com.wjbos.userregistermail.registration.service;

import com.wjbos.userregistermail.appuser.enums.AppUserRole;
import com.wjbos.userregistermail.appuser.model.AppUser;
import com.wjbos.userregistermail.appuser.service.AppUserService;
import com.wjbos.userregistermail.registration.dto.RegistrationRequest;
import com.wjbos.userregistermail.shared.exeptions.NotValidEmailException;
import com.wjbos.userregistermail.registration.validators.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterUserService {

    private AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new NotValidEmailException(String.format("Email %s is not a Valid email", request.getEmail()));
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
