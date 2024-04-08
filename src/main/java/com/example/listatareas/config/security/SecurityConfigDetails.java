package com.example.listatareas.config.security;

import com.example.listatareas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigDetails {

    @Autowired
    public UserRepository userRepository;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            return userRepository.findByUsername(username)
                    .orElseThrow (() -> new RuntimeException("Usuario no encontrado"));
        };
    }
}
