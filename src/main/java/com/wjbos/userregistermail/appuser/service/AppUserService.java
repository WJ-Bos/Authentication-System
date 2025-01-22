package com.wjbos.userregistermail.appuser.service;

import com.wjbos.userregistermail.appuser.model.AppUser;
import com.wjbos.userregistermail.appuser.repository.AppUserRepository;
import com.wjbos.userregistermail.shared.exeptions.EmailAlreadyInUseException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        appUserRepository.save(user);
        //TODO: Send verify token

        return "";
    }
}
