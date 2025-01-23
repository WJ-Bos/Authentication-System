package com.wjbos.userregistermail.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    public void save(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

}
