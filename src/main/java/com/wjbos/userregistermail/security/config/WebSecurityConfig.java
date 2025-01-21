package com.wjbos.userregistermail.security.config;

import com.wjbos.userregistermail.appuser.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf((c) -> c.disable())
                .authorizeHttpRequests(
                        (requests) -> requests

                                .requestMatchers("/api/v*/registration/**")
                                    .permitAll()
                                .anyRequest()
                                .authenticated()

                ).formLogin(form -> form.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        authProvider.setUserDetailsService(appUserService);
        return authProvider;
    }

}
