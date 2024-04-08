package com.example.listatareas.service;

import com.example.listatareas.dto.AuthenticationRequest;
import com.example.listatareas.models.User;
import com.example.listatareas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<String> login(AuthenticationRequest authRequest) {
        try{
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()
            );
            authenticationManager.authenticate(authToken);
            User user = userRepository.findByUsername(authRequest.getUsername()).get();
            String jwt = jwtService.generateToken(user, generateExtraClaims(user));
            return ResponseEntity.ok(jwt);
        } catch (Exception ex)
        {
            if (ex instanceof BadCredentialsException){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario o contraseña incorrecto.");
            }
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    private Map<String, Object> generateExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());

        return extraClaims;

    }
}