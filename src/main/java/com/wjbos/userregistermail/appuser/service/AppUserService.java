package com.wjbos.userregistermail.appuser.service;

import com.wjbos.userregistermail.appuser.model.AppUser;
import com.wjbos.userregistermail.appuser.repository.AppUserRepository;
import com.wjbos.userregistermail.registration.token.ConfirmationToken;
import com.wjbos.userregistermail.registration.token.ConfirmationTokenRepository;
import com.wjbos.userregistermail.registration.token.ConfirmationTokenService;
import com.wjbos.userregistermail.shared.exeptions.EmailAlreadyInUseException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email %s not found",email))
        );
        return user;
    }


    public String signUpUser(AppUser user) {
        boolean userExists = appUserRepository.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new EmailAlreadyInUseException(String.format("User with email %s already exists",user.getEmail()));
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String token = UUID.randomUUID().toString();
        appUserRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.save(confirmationToken);
        //TODO: Send Email
        return token;
    }
}
