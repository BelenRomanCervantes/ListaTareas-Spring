package com.example.listatareas.controllers;

import com.example.listatareas.dto.AuthenticationRequest;
import com.example.listatareas.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authRequest){
        return authenticationService.login(authRequest);
    }
}
